const employeeFirstName= document.getElementById('firstName');
const employeeLastName= document.getElementById('lastName');
const employeeEmail= document.getElementById('email');
const employeeDepartment= document.getElementById("deptId");
const employeeDepartmentName= document.getElementById("deptName");


async function saveEmployee()
{
    let employee = {
        firstName: employeeFirstName.value,
        lastName: employeeLastName.value,
        email: employeeEmail.value,
        deptId:employeeDepartment.value
    };

    await fetch('http://localhost:8082/api/employee-management/',
        {
            method:'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(employee)
        });

    employeeFirstName.value="";
    employeeLastName.value="";
    employeeEmail.value="";
    employeeDepartment.value="";


}

async function saveDepartment()
{
    let department = {
        deptName: employeeDepartmentName.value
    };

    await fetch('http://localhost:8082/api/department/',
        {
            method:'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(department)
        });

    employeeDepartmentName.value="";


}

