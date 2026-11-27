#!/bin/sh
# Shared by the pre-commit and commit-msg hooks.
#
# find_private_data reads text on standard input and prints every line that
# looks like a key, a token or personal data. It prints nothing when the text
# is clean.

# One extended regular expression per line.
private_patterns() {
    cat <<'EOF'
(/Users|/home)/[A-Za-z0-9._-]+/
[A-Za-z]:\\Users\\
trello\.com/[bc]/
-----BEGIN [A-Z ]*PRIVATE KEY-----
gh[pousr]_[A-Za-z0-9]{30,}
github_pat_[A-Za-z0-9_]{30,}
AKIA[0-9A-Z]{16}
AIza[0-9A-Za-z_-]{35}
xox[abprs]-[A-Za-z0-9-]{10,}
(storePassword|keyPassword|password|passwd|secret|api[_-]?key|token)[[:space:]]*[=:][[:space:]]*["'][^"']{8,}["']
EOF
    # Patterns that are private themselves (names, internal hosts) stay out of
    # the repository: put them in .git/info/private-denylist, one per line.
    denylist=$(git rev-parse --git-path info/private-denylist)
    if [ -f "$denylist" ]; then
        grep -Ev '^[[:space:]]*(#|$)' "$denylist"
    fi
}

find_private_data() {
    input=$(cat)
    [ -z "$input" ] && return 0

    private_patterns | while IFS= read -r pattern; do
        [ -z "$pattern" ] && continue
        printf '%s\n' "$input" | grep -E -- "$pattern" | cut -c1-120
    done

    # E-mail addresses, except the ones that are public by design. Kotlin's
    # labels (this@Outer.member, return@forEach.x) have the same shape: skip them.
    printf '%s\n' "$input" \
        | grep -Eo '[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*\.[A-Za-z]{2,}' \
        | grep -Ev '^(this|super|return|break|continue)@' \
        | grep -Ev '@users\.noreply\.github\.com$|@example\.(com|org|net)$|^noreply@anthropic\.com$|^git@github\.com$' \
        | sed 's/^/e-mail address: /'

    return 0
}
