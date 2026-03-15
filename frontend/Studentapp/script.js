const API = "http://localhost:8086/api";


// ---------------- REGISTER ----------------
function register(){

const name = document.getElementById("name").value.trim();
const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value.trim();

if(name === "" || email === "" || password === ""){
alert("All fields are required");
return;
}

fetch(API + "/students/register",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
name:name,
email:email,
password:password
})
})

.then(response=>{
if(!response.ok){
return response.text().then(err => { throw new Error(err); });
}
return response.json();
})

.then(data=>{
alert("Registration Successful");
window.location.href="login.html";
})

.catch(error=>{
console.error(error);
alert(error.message);
});

}



// ---------------- LOGIN ----------------
function login(){

const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value.trim();

if(email === "" || password === ""){
alert("Email and Password required");
return;
}

fetch(API + "/students/login",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
email:email,
password:password
})
})

.then(res=>{
if(!res.ok){
return res.text().then(err => { throw new Error(err); });
}
return res.json();
})

.then(data=>{

localStorage.setItem("studentId", data.id);
localStorage.setItem("studentName", data.name);

window.location.href="dashboard.html";

})

.catch(err=>{
alert(err.message);
});

}



// ---------------- LOGOUT ----------------
function logout(){

localStorage.removeItem("studentId");
localStorage.removeItem("studentName");

window.location.href="login.html";

}



// ---------------- CHECK LOGIN ----------------
function checkLogin(){

const studentId = localStorage.getItem("studentId");

if(!studentId){

alert("Please login first");

window.location.href="login.html";

}

}



// ---------------- LOAD COURSES ----------------
function loadCourses(){

checkLogin();

fetch(API + "/courses")

.then(res=>{
if(!res.ok){
return res.text().then(err => { throw new Error(err); });
}
return res.json();
})

.then(courses=>{

let table="";

courses.forEach(c=>{

table += `
<tr>
<td>${c.id}</td>
<td>${c.name}</td>
<td>${c.description}</td>
<td>${c.duration}</td>
<td>${c.availableSeats}</td>

<td>
<button onclick="enroll(${c.id})">Enroll</button>
</td>

</tr>
`;

});

document.getElementById("courseTable").innerHTML = table;

})

.catch(err=>{
console.error(err);
alert(err.message);
});

}



// ---------------- ENROLL COURSE ----------------
function enroll(courseId){

const studentId = localStorage.getItem("studentId");

fetch(API + "/enroll/" + courseId + "?studentId=" + studentId,{
method:"POST"
})

.then(res=>{
if(!res.ok){
return res.text().then(err => { throw new Error(err); });
}
return res.text();
})

.then(msg=>{

alert(msg);

loadCourses();

})

.catch(err=>{
console.error(err);
alert(err.message);
});

}



// ---------------- LOAD ENROLLMENTS ----------------
function loadEnrollments(){

checkLogin();

const studentId = localStorage.getItem("studentId");

fetch(API + "/students/" + studentId + "/enrollments")

.then(res=>{
if(!res.ok){
return res.text().then(err => { throw new Error(err); });
}
return res.json();
})

.then(data=>{

let table="";

data.forEach(e=>{

table += `
<tr>
<td>${e.courseId}</td>
<td>${e.courseName}</td>

<td>
<button onclick="unenroll(${e.courseId})">Unenroll</button>
</td>

</tr>
`;

});

document.getElementById("enrollTable").innerHTML = table;

})

.catch(err=>{
console.error(err);
alert(err.message);
});

}



// ---------------- UNENROLL ----------------
function unenroll(courseId){

const studentId = localStorage.getItem("studentId");

fetch(API + "/enroll/" + courseId + "?studentId=" + studentId,{
method:"DELETE"
})

.then(res=>{
if(!res.ok){
return res.text().then(err => { throw new Error(err); });
}
return res.text();
})

.then(msg=>{

alert(msg);

loadEnrollments();

})

.catch(err=>{
console.error(err);
alert(err.message);
});

}