<html>
<body>
<h1>${message}</h1>

<form action="/updateAction" method="post">
    <input type="submit" data-qa="player-one-button" name="player" value="Player One"/>
    <input type="submit" data-qa="player-two-button" name="player" value="Player Two"/>
</form>

<div data-qa="score">${score}</div>

<form action = "/resetScore" method="post">
    <input type="submit" data-qa="reset-button" value="Reset Score"/>
</form>
</body>
</html>