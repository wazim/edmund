edmund
======

cryptic crossword solver

### API

JSON
```
http://localhost:15296/edmund/api?pattern=pattern&length=5&format=json
```

XML
```
http://localhost:15296/edmund/api?pattern=pattern&length=5&format=xml
```

Parameter | Type | Description
--- | --- | ---
pattern | string | a number of characters that are known with the character '.' for an unknown symbol.
format | string | either json or xml
