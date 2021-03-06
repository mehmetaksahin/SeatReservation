# SeatReservation

This project includes seat reservation algorithms by following social distancing rules.

Initially, all seats in the hall are available. Each seat has a number. This seat number consists of letters and
numbers. For example: B-3 or F-9

The seats have three states:<br/>
"A" Available<br/>
"-" Not Available<br/>
"S" Sold<br/>

Seats: <br/>

| *          | <b>1</b> | <b>2</b> | <b>3</b> | <b>4</b> | <b>...</b> |
|------------|----------|----------|----------|----------|------------|
| <b>A</b>   | A        | A        | A        | A        | A          |
| <b>B</b>   | A        | A        | A        | A        | A          |
| <b>C</b>   | A        | A        | A        | A        | A          |
| <b>D</b>   | A        | A        | A        | A        | A          |
| <b>...</b> | A        | A        | A        | A        | A          |

<br/>
Latest condition of seats after sale: <br/>

| *          | <b>1</b> | <b>2</b> | <b>3</b> | <b>4</b> | <b>...</b> |
|------------|----------|----------|----------|----------|------------|
| <b>A</b>   | -        | A        | -        | S        | -          |
| <b>B</b>   | S        | -        | A        | -        | A          |
| <b>C</b>   | -        | A        | -        | -        | A          |
| <b>D</b>   | A        | -        | S        | S        | -          |
| <b>...</b> | A        | A        | -        | -        | A          |



