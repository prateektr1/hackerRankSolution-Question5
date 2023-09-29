# NJAN (Nested JSON Array Notation) CLI Tool

NJAN (Nested JSON Array Notation) is a file format that represents nested JSON arrays with certain restrictions. This CLI tool, `njan`, is designed to validate and query elements from files containing text in the NJAN format.

## Features

- **Validation:** Checks if the input text conforms to the NJAN format.
- **Querying:** Allows querying NJAN elements with dot-prefixed sequences of non-negative integers.

## Usage

### Validation

To validate if a file is in NJAN format, use the following command:

```shell
java NjanTool validate < input.json

Steps to clone the repo

1. Clone the Repo https://github.com/prateektr1/hackerRankSolution-Question5.git
2. Import the main file in an IDE like eclipse or intellij and follow the instrcutions given

Querying
To query NJAN elements, use the following command:

shell
Copy code
java NjanTool query <QUERY_STRING> < input.json
Replace <QUERY_STRING> with your query string (e.g., .1, .0.2, or leave it blank for the whole JSON).
The tool will emit the answer to the query on standard output and exit with code 0.
If the query is invalid due to out-of-bounds indices or non-array elements, the tool will exit with code 1.
Examples
Suppose you have a JSON file named input.json containing NJAN text as follows:

json
Copy code
["abc", "def", ["ghi", "jkl"], "mno", [["pqr"]]]
To validate NJAN format:

shell
Copy code
java NjanTool validate < input.json
To query elements:

Query .1:

shell
Copy code
java NjanTool query .1 < input.json
Output: "def"

Query .2.0:

shell
Copy code
java NjanTool query .2.0 < input.json
Output: "ghi"

Query .10 (Invalid):

shell
Copy code
java NjanTool query .10 < input.json
The tool will exit with code 1 as the index is out of bounds.

Query .0.1 (Invalid):

shell
Copy code
java NjanTool query .0.1 < input.json
The tool will exit with code 1 as the result of .0 is a JSON string, which is not an array.

Requirements
Java Development Kit (JDK) 8 or higher.
Note
This is a simplified CLI tool for educational purposes. For production use, you may need to add more error handling, input validation, and consider performance optimizations.


