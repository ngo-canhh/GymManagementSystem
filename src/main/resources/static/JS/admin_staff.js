//thêm sản phẩm
function addNewProduct() {
    const formData = new FormData();
    formData.append("full_name", document.getElementById("name").value);
    formData.append("noID", document.getElementById("noID").value);
    formData.append("age", document.getElementById("age").value);
    formData.append("sex", document.getElementById("sex").value);
    formData.append("date_of_birth", document.getElementById("date_of_birth").value);
    formData.append("phonenumber", document.getElementById("phonenumber").value);
    formData.append("email", document.getElementById("email").value);
    formData.append("bank_account", document.getElementById("bank_account").value);
    formData.append("image", document.getElementById("image").files[0]); // File upload
    formData.append("role", document.getElementById("role").value);
    formData.append("address", document.getElementById("address").value);

    fetch(`/admin/staff/add_new_staff`, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                });
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            const tbody = document.getElementById("tableBody");
            tbody.innerHTML += `
                <tr id="${data.id}">
                    <td>${data.id}</td>
                    <td>${data.full_namename}</td>
                    <td>${data.sex}</td>
                    <td>${data.phonenumber}</td>
                    <td>${data.noID}</td>
                    <td>${data.role}</td>
                    <td><img src="${data.image_url}" alt="" class="img-thumbnail" width="80px"></td>
                    <td>Active</td>
                    <td>
                        <button type="button" class="btn" data-bs-toggle="modal"
                            data-bs-target="#exampleModal" onclick="showInforProduct(${data.id})">
                            ...
                        </button>
                    </td>
                </tr>
            `;
        })
        .catch(error => console.error('Error:', error));
}


//xem thông tin san pham
function showInforStaff(staffID) {
    fetch(`/admin/staff/staffID?staffID=${encodeURIComponent(staffID)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                })
            }
            return response.json();
        })
        .then(staff => {
            document.getElementById("staffID").value = staff.id;
            document.getElementById("staffName").value = staff.full_name;
            document.getElementById("staffNoID").value = staff.noID;
            document.getElementById("staffAge").value = staff.age;
            document.getElementById("staffSex").value = staff.sex;
            document.getElementById("staffDateOfBirth").value = staff.date_of_birth;
            document.getElementById("staffPhonenumber").value = staff.phonenumber;
            document.getElementById("staffEmail").value = staff.email;
            document.getElementById("staffBankAccount").value = staff.bank_account;
            document.getElementById("staffRole").value = staff.role;
            document.getElementById("staffAddress").value = staff.address;
            const tbody = document.getElementById("staffDetailsBodyTable");
            tbody.innerHTML = ``;
            staff.staffDTOs.forEach(staffDTO => {
                tbody.innerHTML += `
                    <tr>
                        <td>${staffDTO.role}</td>
                        <td>${staffDTO.create_date}</td>
                        <td>${staffDTO.status}</td>
                    </tr>
                `;

            })
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`ERROR!!!`);

        })
}

//cập nhật gói tập
function updateStaff() {
    const staff = {
        id: document.getElementById("staffID").value,
        full_name: document.getElementById("staffName").value,
        noID: document.getElementById("staffNoID").value,
        age: document.getElementById("staffAge").value,
        sex: document.getElementById("staffSex").value,
        date_of_birth: document.getElementById("staffDateOfBirth").value,
        phonenumber: document.getElementById("staffPhonenumber").value,
        email: document.getElementById("staffEmail").value,
        bank_account: document.getElementById("staffBankAccount").value,
        role: document.getElementById("staffRole").value,
        address: document.getElementById("staffAddress").value
    }

    console.log(staff);
    

    fetch(`/admin/staff/update_staff`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(staff),
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
            // alert("Thành công");
            const row = document.getElementById(data.id);
            row.innerHTML = `
                <tr id="${data.id}">
                    <td>${data.id}</td>
                    <td>${data.full_namename}</td>
                    <td>${data.sex}</td>
                    <td>${data.phonenumber}</td>
                    <td>${data.noID}</td>
                    <td>${data.role}</td>
                    <td><img src="${data.image_url}" alt="" class="img-thumbnail" width="80px"></td>
                    <td>Active</td>
                    <td>
                        <button type="button" class="btn" data-bs-toggle="modal"
                            data-bs-target="#exampleModal" onclick="showInforProduct(${data.id})">
                            ...
                        </button>
                    </td>
                </tr>
                `;
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`Cap nhat that jvjvjbai`);
        })
}

//tìm kiếm gói tập
document.getElementById("searchForm").addEventListener("submit", function (event) {
    event.preventDefault(); //chặn gửi form mặc định

    const query = document.getElementById("searchInput").value;

    //thêm url 
    fetch(`/admin/product/searchProduct?name=${encodeURIComponent(query)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                })
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById("tableBody")

            tbody.innerHTML = "";

            data.forEach(staff => {
                tbody.innerHTML += `
                    <tr id="${staff.id}">
                        <td>${staff.id}</td>
                        <td>${staff.full_name}</td>
                        <td>${staff.sex}</td>
                        <td>${staff.phonenumber}</td>
                        <td>${staff.noID}</td>
                        <td>${staff.role}</td>
                        <td><img src="${staff.image_url}" alt="" class="img-thumbnail" width="80px"></td>
                        <th th:text="${staff.status}"></th>
                        <td>
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                th:attr="onclick='showInforStaff(' + ${staff.ID} + ')'">
                                ...
                            </button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(error => {
            console.log("Lỗi: ", error.message);
        })
});


//lọc theo 
function filterStaff() {
    const filterSex = document.getElementById("filteSex").value;
    const filterRole = document.getElementById("filterRole").value;
    const filteStatus = document.getElementById("filteStatus").value;
    fetch(`/admin/staff/filter_staff?filterSex=${encodeURIComponent(filterSex)}&filterRole=${encodeURIComponent(filterRole)}&filteStatus=${encodeURIComponent(filteStatus)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                })
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById("tableBody")

            tbody.innerHTML = "";

            data.forEach(staff => {
                tbody.innerHTML += `
                    <tr id="${staff.id}">
                        <td>${staff.id}</td>
                        <td>${staff.full_name}</td>
                        <td>${staff.sex}</td>
                        <td>${staff.phonenumber}</td>
                        <td>${staff.noID}</td>
                        <td>${staff.role}</td>
                        <td><img src="${staff.image_url}" alt="" class="img-thumbnail" width="80px"></td>
                        <th th:text="${staff.status}"></th>
                        <td>
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                th:attr="onclick='showInforStaff(' + ${staff.id} + ')'">
                                ...
                            </button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("ERROR!!!");
        })

}
