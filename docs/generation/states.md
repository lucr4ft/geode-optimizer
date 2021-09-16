# States

There are
- 4 possible states for the top piston
- 3 possible states for the bottom piston
- 2 possible states for all side pistons
for a single budding amethyst when all [rules](./rules) apply.

Possible states for a single budding amethyst with all four sides exposed: `4 * 3 * 2 = 24`

### Interference between Positions

Todo: add description

### Exponential growth of States

(This section does not take the [Interference between Positions](#interference_between_positions) into account!!)

If you have two budding amethysts, each of the 24 possible states of the first one can be followed by all 24 possible states of the second one. That means that each state has 24 possible resulting states. Therefor we have `24 * 24 = 576` possible states with only two budding amethysts.

Let's assume you have 5 budding amethysts that for simplicity do not interfere with each other, you have `24 * 24 * 24 * 24 * 24 = 24^5 = 7962624` possible states.

So the number of possible states growth exponentially with `24^n` for `n` being the number of budding amethysts.

Taking the interference between points into account, the actual number of possible states will most of the time be lower, if not significantly lower than the maximum number of possible states.

### Compression

To reduce memory usage during generation or disk usage when storing the 'states-tree' the states need to be compressed.
The following will describe how this works and how it is implemented.

Todo: add explanation on how the compression works + how it is implemented