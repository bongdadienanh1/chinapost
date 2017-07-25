//插入方法
function addTodo(id,text,db){
    var todo = {
        _id: id,
        title:text,
        completed:false
    }
    db.put(todo,function callback(err,result){
       if(!err){
           console.log('Successfully posted a todo for 【' + id + '】!');
       }
    });
}