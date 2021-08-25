# GeodeSchematic Format

## **v0.0.1**

- GeodeSchematic Version [`first line`]
- dimension of the schematic (width x height x depth)  [`12 bytes`]
- list of indices (`size = width * height * depth`)  [`size * 4 bytes`]
  - every block in the schematic dimensions is represented by a index, wich referers to a unique `BlockState` 
  - index of `-1` means no block
- list of unique blockstates as string seperated by `;`
  - format: `$block_id[$property_name=$property_value,...]`
