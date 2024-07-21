const employeeFirstName= document.getElementById('firstName');
const employeeLastName= document.getElementById('lastName');
const employeeEmail= document.getElementById('email');
async function getEmployeeById()
{
    let id= localStorage.getItem("employeeId");
    const res = await fetch('http://localhost:8082/api/employee-management/'+id);
    const employee  = await res.json();
    employeeFirstName.value=`${employee.firstName}`;
    employeeLastName.value=`${employee.lastName}`;
    employeeEmail.value=`${employee.email}`

}

async function updateEmployee()
{
    let employeeId= localStorage.getItem("employeeId");
    let employee = {
        id:employeeId,
        firstName: employeeFirstName.value,
        lastName: employeeLastName.value,
        email: employeeEmail.value
    };

    await fetch('http://localhost:8082/api/employee-management/'+employeeId,
        {
            method:'PUT',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(employee)
        });
    console.log(employee);
    employeeFirstName.value="";
    employeeLastName.value="";
    employeeEmail.value="";


}