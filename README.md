**PDF Form Validator**<br>
A Java-based headless PDF validation engine designed for CI/CD environments. The framework separates test data from core logic and leverages Maven to optimize the build lifecycle, showcasing a scalable and maintainable approach to automated software testing.<br>

This tool automates PDF form population and generates visual audit reports that clearly highlight field mappings, enabling fast and accurate validation of form data.<br>

Instead of relying solely on logs or assertions, each processed file produces an annotated PDF output, allowing reviewers to instantly verify correctness.

🎯 Engineering Goals<br>
Automated PDF form population using Apache PDFBox<br>
Visual validation through annotated output files<br>
Scalable batch processing of multiple PDFs without code changes<br>
CI/CD integration using GitHub Actions for automated validation workflows

🧭 How It Works<br>
PDF files are placed in the /demo-files directory<br>
The application processes each file and injects data into form fields<br>
An audited PDF is generated with visual indicators for field mappings<br>
Results can be reviewed locally or downloaded from CI pipeline artifacts

✅ Acceptance Criteria (Definition of Done)<br>
For a validation run to be considered successful, the following must be true:<br>
Input Handling: The engine must identify all fillable fields within a PDF located in the /demo-files directory.<br>
Data Mapping: Injected data must persist in the output file without corrupting the original PDF structure.<br>
Visual Feedback: The output Audit PDF must contain annotations for every field targeted by the validation logic.<br>
Pipeline Integrity: The GitHub Action must fail if a PDF is unreadable or if the Maven build encounters a dependency error.<br>
Artifact Generation: A verified audit-results-package (ZIP) must be produced and available for download within 60 seconds of the CI/CD trigger.

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

💡 Key Value<br>
Traditional PDF validation relies on logs and assertions, which can miss visual issues.<br>
This project introduces visual QA validation, making it easy to:<br>
Detect incorrect mappings<br>
Identify missing or misaligned fields<br>
Verify output quickly without manual inspection

🛠 Tech Stack<br>
Language: Java 21 (LTS)<br>
Libraries: Apache PDFBox, Maven<br>
CI/CD: GitHub Actions (automated validation pipelines)<br>
Architecture: Headless validation engine integrated into CI/CD pipelines 
