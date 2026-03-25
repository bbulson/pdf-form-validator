PDF Form Validator

A Java-based headless automation tool designed for CI/CD environments, focused on validating PDF form data through visual audit reporting.

This project is designed to simulate real-world QA validation by not only populating PDF forms, but also visually confirming accuracy. Each processed file produces an annotated output that highlights fields and points directly to mapped values, making it easy to verify correctness at a glance.

🚀 What This Project Demonstrates

Automated PDF form population using Apache PDFBox

Visual validation through annotated output files

Scalable processing of multiple forms with no code changes

CI/CD integration using GitHub Actions for repeatable test execution

🧭 How It Works

PDF files are placed in the /demo-files directory
The application processes each file and injects data into all form fields
A new audited PDF is generated with visual indicators showing field mappings
Results can be reviewed locally or downloaded from GitHub Actions

📁 Project Structure

/src → Core Java logic for PDF processing and validation

/demo-files → Sample input files for testing

.github/workflows → CI pipeline that runs validation automatically

AuditPDF.java → Main execution class

▶️ Getting Started

Run Locally

mvn clean package

java -jar target/GenerateTestPDF-1.0-SNAPSHOT.jar ./demo-files/your-file.pdf

Run via GitHub Actions

Add or update a PDF in /demo-files

Push changes

View results in the Actions tab → Artifacts

💡 Key Value

This project focuses on visual validation, which is often missing in traditional automation. Instead of relying only on logs or assertions, it provides a clear, human-readable way to confirm that every field is mapped correctly.
