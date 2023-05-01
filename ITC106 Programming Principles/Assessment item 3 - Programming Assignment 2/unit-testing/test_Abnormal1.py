import sys
import os
import pytest
sys.path.insert(0, os.path.abspath('..'))
import a3

def test_main_abnormal1(monkeypatch, capsys):
    test_input = "Abnormal1.txt"
    expected_output = (
"""----------------------
WELCOME TO UTOPIA BANK
----------------------


** Found the following transactions **

Invalid value: "w" on line 2. Expected a non-negative whole integer.
Invalid value: "2.5" on line 4. Expected a non-negative whole integer.
Invalid value: "*" on line 7. Expected a non-negative whole integer.
Invalid value: "-1" on line 9. Expected a non-negative whole integer.

** Transaction processing failed. Exiting. **

"""
    )
    monkeypatch.setattr('builtins.input', lambda _: test_input)
    
    a3.main()
    captured = capsys.readouterr()
    
    # Print the actual and expected output
    print("Actual output:")
    print(captured.out)
    print("Expected output:")
    print(expected_output)

    assert captured.out == expected_output
