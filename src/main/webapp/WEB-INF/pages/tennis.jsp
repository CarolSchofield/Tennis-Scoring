<html>
<body>
<h1>${message}</h1>

<form action="/updateAction" method="post">
    <input type="submit" data-qa="player-one-button" value="Player One"/>

</form>

<div data-qa="player-one-score">${score}</div>

<form action = "/resetScore" method="post">
    <input type="submit" data-qa="reset-button" value="Reset Score"/>
</form>
</body>
</html>