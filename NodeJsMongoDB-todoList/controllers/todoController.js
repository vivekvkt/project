

var bodyParser = require('body-parser');
var mongoose = require('mongoose');
// connect 
mongoose.connect('mongodb://test12:test1234@ds247317.mlab.com:47317/vivek');

// create a schema

var todoSchema = new mongoose.Schema({

	item : String
});

var Todo = mongoose.model('Todo', todoSchema);

var itemOne = new Todo({item: 'my flower'}).save(function(err){

if (err) throw err;
console.log('Item has been  saved');

});




//var data = [{item:"apple"}, {item:"pen"}, {item:"Do some coding"},{item:"hello India"},{item:"banana date: 12/01/2018"}];



//var urlencodedParser =  bodyParser.urlencoded({ extended: false });


//var bodyParser = require('body-parser');

var data = [];

var urlencodedParser =  bodyParser.urlencoded({ extended: false });

module.exports =  function(app){

app.get('/todo', function(req,res){

	Todo.find({}, function(err,data){

		if(err) throw err;
		res.render('todo',{todos:data});
	});

  // res.render('todo',{todos:data});
  //res.render('todo',{todos:data});


});


app.post('/todo',urlencodedParser,function(req,res){

	// get data from the view and add it mongodb
	var newTodo = Todo(req.body).save(function(err, data) {
		
		if(err) throw err;
		res.json(data);
	})

//data.push(req.body);
//res.json(data);
});

app.delete('/todo/:item', function(req,res){
	// delete the  requested item from 

	Todo.find({item:req.params.item.replace(/\_/g, " ")}).remove(function(err,data){

		if(err) throw err;
		res.json(data);
	});
	
	

	/*
	data  = data.filter(function(todo){
		return todo.item.replace(/ /g, '_')!==req.params.item;
	});
	res.json(data);
	*/

});

}