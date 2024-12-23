let satff = null;

function showInforPT(ptID) {
    fetch(`/admin/pt/PTDetails?ptID=${encodeURIComponent(ptID)}`)
        .then(respone => {
            if (!respone.ok) {
                return respone.text().then(message => {
                    throw new Error(message);
                })
            } else {
                return respone.json();
            }
        })
        .then(pt => {
            console.log(pt);
            staff = pt.staff;
            document.getElementById("staffID").value = pt.id;
            document.getElementById("staffName").value = pt.staff.full_name;
            document.getElementById("staffNoID").value = pt.staff.noID;
            document.getElementById("staffAge").value = pt.staff.age;
            document.getElementById("staffSex").value = pt.staff.sex;
            document.getElementById("staffDateOfBirth").value = pt.staff.date_of_birth;
            document.getElementById("staffPhonenumber").value = pt.staff.phonenumber;
            document.getElementById("staffEmail").value = pt.staff.email;
            document.getElementById("staffBankAccount").value = pt.staff.bank_account;
            document.getElementById("ptDescription").value = pt.description;
            document.getElementById("ptField").value = pt.field;
            document.getElementById("ptcategory").value = pt.category;
            document.getElementById("ptMax").value = pt.max;
            document.getElementById("ptMin").value = pt.min;
            const listService = document.getElementById("listService");
            listService.innerHTML = ``;
            pt.services.forEach(service => {
                if (service.status == 'Hoạt động') {
                    console.log(service.service);
                    
                    const encodedService = encodeURIComponent(JSON.stringify(service.service));
                    listService.innerHTML += `
                        <li data-service='${encodedService}' class = "row">
                            <span class="col-10">${service.service.name}</span>
                            <button class="btn btn-sm btn-close ms-2 col-1" onclick="removeService(this)"></button>
                        </li>
                    `
                }
            });

        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("NOT FOUND");
        })
}

function searchPT() {
    const value = document.getElementById("searchInput").value.trim().toLowerCase();
    fetch(`/admin/pt/searchPT?name=${encodeURIComponent(value)}`)
        .then(respone => {
            if (!respone.ok) {
                return respone.text().then(message => {
                    throw new Error(message);
                })
            } else {
                return respone.json();
            }
        })
        .then(pts => {
            const tbody = document.getElementById("tableBody");
            tbody.innerHTML = ``;
            pts.forEach(pt => {
                tbody.innerHTML += `
                <tr id="${pt.id}">
                    <td>${pt.id}</td>
                    <td>${pt.staff.full_name}</td>
                    <td>${pt.staff.phonenumber}</td>
                    <td>${pt.staff.sex}</td>
                    <td>${pt.category}</td>
                    <td>${pt.field}</td>
                    <td><img src="${pt.staff.image_url}" alt="ảnh" class="img-thumbnail" width="80px"></td>
                    <td>
                        <button type="button" class="btn" data-bs-toggle="modal"
                            data-bs-target="#exampleModal" onclick="showInforPT(${pt.id})">
                            ...
                        </button>
                    </td>
                </tr>
            `

            })
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("NOT FOUND");
        })
}

function getAllService() {
    fetch('/admin/pt/getAllService')
        .then(response => {  // Sửa lại 'respone' thành 'response'
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                });
            } else {
                return response.json();
            }
        })
        .then(services => {
            const allServices = document.getElementById("allsevice");
            allServices.innerHTML = ``;  // Xóa nội dung cũ trước khi thêm mới
            services.forEach(service => {
                if (service.status === 'Hoạt động') {  // Kiểm tra trạng thái dịch vụ
                    // Mã hóa đối tượng service để tránh lỗi khi truyền vào onclick
                    const encodedService = encodeURIComponent(JSON.stringify(service));
                    allServices.innerHTML += `
                        <li>
                            <button class="dropdown-item" 
                                onclick="addService('${encodedService}')">
                                ${service.name}
                            </button>
                        </li>
                    `;
                }
            });
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert("NOT FOUND");
        });
}

function addService(encodedService) {
    const decode = JSON.parse(decodeURIComponent(encodedService));
    const listService = document.getElementById("listService");
    listService.innerHTML += `
        <li data-service='${encodedService}' class = "row">
            <span class="col-10">${decode.name}</span>
            <button class="btn btn-sm btn-close ms-2 col-1" onclick="removeService(this)"></button>
        </li>
    `
}

function removeService(button) {
    const listItem = button.parentElement;
    listItem.remove();
}

function updatePT() {
    const ptID = document.getElementById("staffID").value;
    const category = document.getElementById("ptcategory").value;
    const field = document.getElementById("ptField").value;
    const description = document.getElementById("ptDescription").value;
    const max = document.getElementById("ptMax").value;
    const min = document.getElementById("ptMin").value

    // Collect the list of services
    const services = [];
    const serviceElements = document.querySelectorAll("#listService li");
    serviceElements.forEach(serviceElement => {
        const encodeService = serviceElement.getAttribute("data-service")
        serviceDTO = {
            service: JSON.parse(decodeURIComponent(encodeService)),
            status: 'Hoạt động'
        }
        services.push(serviceDTO);

        console.log(serviceDTO);

    });

    const pt = {
        id: ptID,
        staff: staff,
        category: category,
        field: field,
        description: description,
        min: min,
        max: max,
        services: services
    }

    console.log(pt);


    fetch(`/admin/pt/updatePT`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(pt)
    })

}



