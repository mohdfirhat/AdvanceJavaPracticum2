## Cleaning idea
trim and replaceFirst(input.trim().replaceFirst("^\\d+\\.\\s*", "");)
replace multi space to single space.(cleaned.split("\\s+");)

## Validate
use multi titlecase

### Regex for multiple titlecase(Java syntax)
```java
String regex = "^([A-Z][a-z]*)(\\s[A-Z][a-z]*)*$";
```

### Regex for single titlecase(Java syntax)
```java
String regex = "^[A-Z][a-z]*$";
```
