---
name: code-review
description: 
  Reviews code changes in a pull request. Use when reviewing a PR, or when the user asks for a code review.
  Scan only .java files, and ignore any other file types.
  Check for improvements to code quality. Provide feedback on design patterns, architecture, and best practices. Suggest refactoring opportunities and potential bugs or edge cases.
Specifically, check for:
  - Code duplication
  - Unused variables or imports
  - Potential bugs or edge cases
  - Performance issues
  - Security vulnerabilities
  - Swallowed exceptions
---

When running a code review:

1. Run `git diff main...HEAD` to see all changes on this branch
2. Write a description following this format:

## Issues found : 
List the issues found while reviewing the code.
List any potential bugs, edge cases, or performance issues

## Severity : 
- Low: Minor issues that do not affect functionality or performance
- Medium: Issues that may affect functionality or performance, but can be fixed without major changes
- High: Issues that may cause functionality or performance problems, and require immediate attention


## Suggestions : 
- List the suggestions for improving code quality
- Suggest refactoring opportunities and potential bugs or edge cases