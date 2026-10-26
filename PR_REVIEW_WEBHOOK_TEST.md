This small documentation change verifies that the Enclave local PR review webhook starts a review run when a pull request is opened.
A follow-up commit verifies that updates to the open pull request also start a review run.
A post-restart commit verifies that the local webhook starts a new review run.
A fresh commit after the latest restart verifies that PR review runs with ENABLE_LOCAL_PR_ANALYSIS enabled.

A fresh post-gateway-key commit verifies that local PR review calls the configured AI gateway.
