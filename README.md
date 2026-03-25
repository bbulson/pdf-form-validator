PDF Form Validator

A Java-based headless PDF validation engine designed for CI/CD environments.<br>
This tool automates PDF form population and generates visual audit reports that clearly highlight field mappings, enabling fast and accurate validation of form data.<br>

Instead of relying solely on logs or assertions, each processed file produces an annotated PDF output, allowing reviewers to instantly verify correctness.

🎯 Engineering Goals<br>
Automated PDF form population using Apache PDFBox<br>
Visual validation through annotated output files<br>
Scalable batch processing of multiple PDFs without code changes<br>
CI/CD integration using GitHub Actions for automated validation workflows

🧭 How It Works

PDF files are placed in the /demo-files directory<br>
The application processes each file and injects data into form fields<br>
An audited PDF is generated with visual indicators for field mappings<br>
Results can be reviewed locally or downloaded from CI pipeline artifacts

📁 Project Structure<br>
/src → Core Java logic for PDF processing and validation<br>
/demo-files → Sample input files for testing<br>
.github/workflows → CI pipeline for automated validation<br>
AuditPDF.java → Main execution entry point

▶️ Getting Started<br>
Add or update PDFs in the /demo-files directory<br>
Push changes to trigger the CI pipeline automatically<br>
Download validation results from the workflow artifacts<br>

For local testing, see /demo-files/README.md.

💡 Key Value

Traditional PDF validation relies on logs and assertions, which can miss visual issues.<br>
This project introduces visual QA validation, making it easy to:

Detect incorrect mappings<br>
Identify missing or misaligned fields<br>
Verify output quickly without manual inspection
🛠 Tech Stack
Language: Java 21 (LTS)<br>
Libraries: Apache PDFBox, Maven<br>
CI/CD: GitHub Actions (automated validation pipelines)<br>
Architecture: Headless validation engine integrated into CI/CD pipelines
