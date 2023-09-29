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
