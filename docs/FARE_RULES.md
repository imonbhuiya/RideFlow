# Fare calculation

`FareCalculator.estimate(rideType, distanceKm)` returns a EUR demonstration fare as a `BigDecimal`.

| Ride type | Base fare | Per kilometre |
| --- | ---: | ---: |
| X | 3.00 | 1.50 |
| XL | 3.00 | 2.00 |
| XXL | 3.00 | 2.50 |

Formula: base fare + (distance in kilometres × category rate).

For 10 km, X costs 18.00, XL costs 23.00, and XXL costs 28.00. Results are rounded once, at the end, to two decimal places using `HALF_UP`.

Distance must be greater than zero. Null ride types and distances are rejected. Construct decimal inputs from strings, such as `new BigDecimal("2.5")`, to avoid binary floating-point conversion surprises.

These are learning-project rates, not live transport prices. The estimate does not currently include waiting time, taxes, surge pricing, or a payment transaction.
