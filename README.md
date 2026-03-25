PDF Form Validator

A Java-based headless automation tool designed for CI/CD environments, focused on validating PDF form data through visual audit reporting.<br>
This project is designed to simulate real-world QA validation by not only populating PDF forms, but also visually confirming accuracy. Each processed file produces an annotated output that highlights fields and points directly to mapped values, making it easy to verify correctness at a glance.

🎯 Engineering Goals

Automated PDF form population using Apache PDFBox<br>
Visual validation through annotated output files<br>
Scalable processing of multiple forms with no code changes<br>
CI/CD integration using GitHub Actions (YAML workflow) to automatically process and validate all PDF files

🧭 How It Works

PDF files are placed in the /demo-files directory<br>
The application processes each file and injects data into all form fields<br>
A new audited PDF is generated with visual indicators showing field mappings<br>
Results can be reviewed locally or downloaded from GitHub Actions

📁 Project Structure

/src → Core Java logic for PDF processing and validation<br>
/demo-files → Sample input files for testing<br>
.github/workflows → CI pipeline that runs validation automatically<br>
AuditPDF.java → Main execution class

▶️ Getting Started

Add or update PDFs in the demo-files/ directory on GitHub to automatically trigger the pipeline. For local testing, see demo-files/README.md.

💡 Key Value

This project focuses on visual validation, which is often missing in traditional automation. Instead of relying only on logs or assertions, it provides a clear, human-readable way to confirm that every field is mapped correctly.

🛠 Tech Stack
* **Language:** Java 17 (OpenSDK)
* **Libraries:** Apache PDFBox, Maven
* **CI/CD:** GitHub Actions (YAML-based pipelines)
* **Architecture:** Headless / Automation-as-Code
