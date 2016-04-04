# Byteman Mini Demo with Example Application
## Contents

- Folder `rules`: Example Byteman rules for AnimalGame app
- Folder `src`: Example app sourcecode

## Requirements

- Maven, java-1.8.0-openjdk
- Byteman >= 3.0.4
- F23 or equivalent with byteman from rawhide:
  see: http://koji.fedoraproject.org/koji/buildinfo?buildID=744745

## Running

```
  $ mvn package
  $ ./run
```

## Example Application

The example application as included here is a simple program suitable
for starting/stopping 'Threads' (a.k.a animals). When the program runs
output is printed to file `out.log`.
