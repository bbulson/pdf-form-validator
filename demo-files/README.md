Sample PDF forms used for automated validation testing and CI pipeline execution.

📌 Purpose<br>
Separates test data from application source code<br>
Enables quick validation testing without modifying logic<br>
Serves as input for both local runs and CI pipeline execution

▶️ How to Use<br>
Add any .pdf form to this directory<br>
Commit and push changes to the repository<br>
The CI pipeline (via GitHub Actions) will automatically:<br>
 - Process all PDFs in this folder<br>
 - Generate validated and annotated outputs<br>
View results:<br>
 - Navigate to the Actions tab<br>
 - Open the latest workflow run<br>
 - Download artifacts from the “audit-results-package”

💡 Notes
No configuration is required when adding new files
All PDFs in this directory are processed automatically
Output files include visual audit annotations for easy validation review
