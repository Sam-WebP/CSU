import sys
import os
import pytest

# Add the parent directory to the Python path
sys.path.insert(0, os.path.abspath('..'))

import a3

def test_main_abnormal1(monkeypatch, capsys):
    test_input = "Abnormal2.txt"
    expected_output = (
        "Invalid value: \"\" on line 5. Value cannot be empty.\n"
        "Invalid value: \"\" on line 8. Value cannot be empty.\n"
    )
    monkeypatch.setattr('builtins.input', lambda _: test_input)
    
    a3.main()
    captured = capsys.readouterr()

    assert expected_output in captured.out
