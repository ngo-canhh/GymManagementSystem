function addNewCustomer() {
    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;
    const sex = document.getElementById("sex").value;
    const phonenumber = document.getElementById("phonenumber").value;
    const email = document.getElementById("email").value;
    const date_of_birth = document.getElementById("date_of_birth").value;
    const category = document.getElementById("category").value;
    const address = document.getElementById("address").value;

    const data = {
        full_name: name,
        age: age,
        sex: sex,
        phonenumber: phonenumber,
        email: email,
        date_of_birth: date_of_birth,
        category: category,
        address: address
    }

    fetch(`/admin/customer/add_new_customer`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                })
            }
            return response.json();
        })
        .then(data => {
            const table = document.getElementById("tableBody");
            table.innerHTML += `
                    <tr id="${data.id}">
                        <td>${data.id}</td>
                        <td>${data.full_name}</td>
                        <td>${data.phonenumber}</td>
                        <td>${data.age}</td>
                        <td>${data.sex}</td>
                        <td>${data.category}</td>
                        <td>${data.create_date}</td>
                        <td>
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#detailsCustomer"
                                th:attr="onclick='showInforCustomer(' + ${data.id} + ')'">...</button>
                        </td>
                    </tr>
            `;
            console.log(data);
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`Thêm khách hàng thất bại!`);

        });
}

function showInforCustomer(customerID) {
    fetch(`/admin/customer/customer_id?customerID=${encodeURIComponent(customerID)}`)
        .then(respone => {
            if (!respone.ok) {
                return respone.text().then(message => {
                    throw new Error(message);
                })
            } else {
                return respone.json();
            }
        })
        .then(customer => {
            document.getElementById("customerID").value = customer.id;
            document.getElementById("customerName").value = customer.full_name;
            document.getElementById("customerAge").value = customer.age;
            document.getElementById("customerSex").value = customer.sex;
            document.getElementById("customerPhone").value = customer.phonenumber;
            document.getElementById("customerEmail").value = customer.email;
            document.getElementById("customerBirth").value = customer.date_of_birth;
            document.getElementById("customerCreateDate").value = customer.create_date;
            document.getElementById("customerCategory").value = customer.category;
            document.getElementById("customerAddress").value = customer.address;
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("NOT FOUND");
        })
}

function updateCustomer() {
    const customer = {
        id: document.getElementById("customerID").value,
        full_name: document.getElementById("customerName").value,
        age: document.getElementById("customerAge").value,
        sex: document.getElementById("customerSex").value,
        phonenumber: document.getElementById("customerPhone").value,
        email: document.getElementById("customerEmail").value,
        date_of_birth: document.getElementById("customerBirth").value,
        create_date: document.getElementById("customerCreateDate").value,
        category: document.getElementById("customerCategory").value,
        address: document.getElementById("customerAddress").value
    }

    fetch(`/admin/customer/update_customer`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(customer)
    })
        .then(respone => {
            if (!respone.ok) {
                return respone.text().then(message => {
                    throw new Error(message);
                })
            } else {
                return respone.json();
            }
        })
        .then(customer => {
            const row = document.getElementById(customer.id);
            row.innerHTML = `
                <td>${customer.id}</td>
                <td>${customer.full_name}</td>
                <td>${customer.phonenumber}</td>
                <td>${customer.age}</td>
                <td>${customer.sex}</td>
                <td>${customer.category}</td>
                <td>${customer.create_date}</td>
                <td>
                    <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#detailsCustomer"
                        th:attr="onclick='showInforCustomer(' + ${customer.id} + ')'">...</button>
                </td>
            `;
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("ERROR!!!");
        })

}

function filterCustomer() {
    const filterSex = document.getElementById("filteSex").value;
    const filterCategory = document.getElementById("filterCategory").value;

    fetch(`/admin/customer/filter_customer?sex=${encodeURIComponent(filterSex)}&category=${encodeURIComponent(filterCategory)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                })
            }else {
                return response.json();
            }
        })
        .then(data => {
            const table = document.getElementById("tableBody");
            table.innerHTML = "";
            data.forEach(customer => {
                console.log(customer);
                
                table.innerHTML += `
                    <tr id="${customer.id}">
                        <td>${customer.id}</td>
                        <td>${customer.full_name}</td>
                        <td>${customer.phonenumber}</td>
                        <td>${customer.age}</td>
                        <td>${customer.sex}</td>
                        <td>${customer.category}</td>
                        <td>${customer.create_date}</td>
                        <td>
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#detailsCustomer"
                                onclick="showInforCustomer(${customer.id})">...</button>
                        </td>
                    </tr>
                `;
            })
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("ERROR!!!");
        })
}

document.getElementById("searchForm").addEventListener("submit", function (event) {
    event.preventDefault(); //chặn gửi form mặc định

    const searchquery = document.getElementById("searchInput").value;
    const query = searchquery.trim();
    console.log(query);
    
    //thêm url 
    fetch(`/admin/customer/searchCustomer?NameOrPhone=${encodeURIComponent(query)}`)
        .then(respone => respone.json())
        .then(data => {
            const tbody = document.getElementById("tableBody")

            tbody.innerHTML = "";

            data.forEach(customer => {
                tbody.innerHTML += `
                        <tr id="${customer.id}">
                            <td>${customer.id}</td>
                            <td>${customer.full_name}</td>
                            <td>${customer.phonenumber}</td>
                            <td>${customer.age}</td>
                            <td>${customer.sex}</td>
                            <td>${customer.category}</td>
                            <td>${customer.create_date}</td>
                            <td>
                                <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#detailsCustomer"
                                    onclick="showInforCustomer(${customer.id})">...</button>
                            </td>
                        </tr>
                    `;
            });
        })
        .catch(error => {
            console.log(error);
        })
});