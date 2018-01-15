var express = require('express');


var todoController = require('./controllers/todoController');
var app =  express();

// set up templet engine


app.set('view engine','ejs');
// static files

app.use(express.static('./public'));

//fire

todoController(app);

//localhost:300/assets/styles.css

//listen to port

app.listen(3000);

console.log('listen port are 3000');