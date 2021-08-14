# GeodeOptimizer - Commands

- `go pos [1-2]`:

    description: "set positions"
- `go gen`

    requirements:
    - positions 1 and 2 have been set
  
    description: "generate optimal piston layout"
- `go preview [true/false]`:

    requirements:
    - `go gen` has been executed before

    description: "toggle preview"
- `go set`:

    requirements:
    - op
    - creative
    - `go gen` has been executed before
  
    description: "place generated blocks"
- `go undo`:

    description: "undo `go set`"