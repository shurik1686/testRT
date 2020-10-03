// Задание 1.Б
function ChangeOverTd(x) {
 document.getElementById('edits').innerHTML = document.getElementById('id'+x.id).innerHTML +','+x.innerHTML ;
 document.getElementById('edits').style = "background-color:"+x.innerHTML 
};
 