edmund
======

<a href="https://travis-ci.org/wazim/edmund">
<img src="https://travis-ci.org/wazim/edmund.svg?branch=master">
</a>

crossword solver

### API

JSON
```
http://localhost:15296/edmund/api?pattern=pattern&format=json
```

XML
```
http://localhost:15296/edmund/api?pattern=pattern&format=xml
```

Parameter | Type | Description
--- | --- | ---
pattern | string | a number of characters that are known with the character '.' for an unknown symbol.
format | string | either json or xml
