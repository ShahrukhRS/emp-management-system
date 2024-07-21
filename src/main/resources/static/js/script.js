

async function getEmployees() {
    const res = await fetch('http://localhost:8082/api/employee-management/employees');
    const employees = await res.json();
    let tableData=document.querySelector(".emp_table");
    console.log(employees);
    const updateLink="/update-form";
    const listEmployee= "/view-employees";
    let element="";
    employees.map(employee=>(
        element+=`<tr><td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td>${employee.email}</td>
        <td>${employee.deptName}</td>
        <td><a onclick="getEmployeeById(${employee.id})" href="${updateLink}">Update</a>
        <a onclick="deleteEmployee(${employee.id}); if (!(confirm('Are you sure you want to delete this Employee?' ))) return false";  href="${listEmployee}">Delete</a>
        </td>
        </tr>`
    ))
    tableData.innerHTML=element;
}
async function getEmployeeById(id) {
    localStorage.setItem("employeeId",id);

}


async function deleteEmployee(id)
{
    let response= await fetch("http://localhost:8082/api/employee-management/"+id, {
        method: 'DELETE',
    }).catch(reportError)

    let message =response.json()

}