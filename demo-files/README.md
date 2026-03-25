This folder contains sample PDF forms used to demonstrate and test the validator.

📌 Purpose

Keeps test data separate from source code

Allows easy testing without modifying application logic

Acts as the input source for both local runs and CI automation

▶️ How to Use

Add any .pdf form to this folder

Push changes to the main branch to automatically trigger the GitHub Actions validation pipeline.

The system will automatically process all files in this directory

💡 Notes

No configuration is required when adding new files

All PDFs placed here will be included in the next execution

Output files will be generated with audit annotations for review
