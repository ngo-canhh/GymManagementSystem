//thêm gói tập
function addNewService() {
    const name = document.getElementById("inputServiceName").value;
    const category = document.getElementById("inputCategory").value;
    const sale_price = document.getElementById("inputSalePrice").value;
    const pt_persentage = document.getElementById("inputPTPersentage").value;
    const gym_persentage = document.getElementById("inputGymPersentage").value;
    const number_of_sessions = document.getElementById("inputNumberSession").value;
    const frequency = document.getElementById("inputFrequency").value;
    const description = document.getElementById("inputDescription").value;
    const status = document.getElementById("inputStatus").value;

    const new_service = {
        name: name,
        category: category,
        sale_price: sale_price,
        pt_persentage: pt_persentage,
        gym_persentage: gym_persentage,
        number_of_sessions: number_of_sessions,
        frequency: frequency,
        description: description,
        status: status
    }

    fetch(`/admin/service/add_new_service`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(new_service)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById("tableBody")
            tbody.innerHTML += `
                            <tr id="${data.id}">
                                <td>${data.id}</td>
                                <td>${data.name}</td>
                                <td>${data.category}</td>
                                <td>${data.sale_price}</td>
                                <td>
                                    <button type="button" class="btn" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal" onclick="showInforService(${data.id})">
                                        ...
                                    </button>
                                </td>
                            </tr>
                        `;

        })
        .catch(error => console.error('Error:', error));


}

//xem thông tin gói tập
function showInforService(serviceID) {
    const id = document.getElementById("serviceID");
    const name = document.getElementById("serviceName");
    const category = document.getElementById("category");
    const sale_price = document.getElementById("salePrice");
    const pt_persentage = document.getElementById("PTPersentage");
    const gym_persentage = document.getElementById("GymPersentage");
    const number_of_sessions = document.getElementById("NumberSession");
    const frequency = document.getElementById("Frequency");
    const description = document.getElementById("Description");
    const status = document.getElementById("status");
    fetch(`/admin/service/serviceID?serviceID=${encodeURIComponent(serviceID)}`)
        .then(respone => respone.json())
        .then(service => {
            id.value = service.id;
            name.value = service.name;
            category.value = service.category;
            sale_price.value = service.sale_price;
            pt_persentage.value = service.pt_persentage;
            gym_persentage.value = service.gym_persentage;
            number_of_sessions.value = service.number_of_sessions;
            frequency.value = service.frequency;
            description.value = service.description;
            status.value = service.status;
        })
        .catch(error => {
            console.log(error);

        })
}

//cập nhật gói tập
function updateService() {
    const id = document.getElementById("serviceID");
    const name = document.getElementById("serviceName");
    const category = document.getElementById("category");
    const sale_price = document.getElementById("salePrice");
    const pt_persentage = document.getElementById("PTPersentage");
    const gym_persentage = document.getElementById("GymPersentage");
    const number_of_sessions = document.getElementById("NumberSession");
    const frequency = document.getElementById("Frequency");
    const description = document.getElementById("Description");
    const status = document.getElementById("status");
    const service = {
        id: id.value,
        name: name.value,
        category: category.value,
        sale_price: sale_price.value,
        pt_persentage: pt_persentage.value,
        gym_persentage: gym_persentage.value,
        number_of_sessions: number_of_sessions.value,
        frequency: frequency.value,
        description: description.value,
        status: status.value
    }

    fetch(`/admin/service/upadate_service`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(service),
    })
        .then(respone => {
            if (respone.ok) {
                return respone.json();
            } else {
                alert("Cập nhật thất bại!!!");
                return response.json().then(errorData => {
                    throw new Error(errorData.message || "Cập nhật thất bại.");
                });
            }
        })
        .then(data => {
            // alert("Thành công");
            const row = document.getElementById(data.id);
            row.innerHTML = `
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.category}</td>
                    <td>${data.sale_price}</td>
                    <td>${data.status}</td>
                    <td>
                        <button type="button" class="btn" data-bs-toggle="modal"
                            data-bs-target="#exampleModal" onclick="showInforService(${data.id})">
                            ...
                        </button>
                    </td>
                `;
        })
        .catch(error => { console.log(error); })
}

//tìm kiếm gói tập
document.getElementById("searchForm").addEventListener("submit", function (event) {
    event.preventDefault(); //chặn gửi form mặc định

    const query = document.getElementById("searchInput").value;

    //thêm url 
    fetch(`/admin/service/searchService?IDOrName=${encodeURIComponent(query)}`)
        .then(respone => respone.json())
        .then(data => {
            const tbody = document.getElementById("tableBody")

            tbody.innerHTML = "";

            data.forEach(service => {
                tbody.innerHTML += `
                            <tr id="${service.id}">
                                <td>${service.id}</td>
                                <td>${service.name}</td>
                                <td>${service.category}</td>
                                <td>${service.sale_price}</td>
                                <td>${service.status}</td>
                                <td>
                                    <button type="button" class="btn" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal" onclick="showInforService(${service.id})">
                                        ...
                                    </button>
                                </td>
                            </tr>
                        `;
            });
        })
        .catch(error => {
            console.log(error);
        })
});


//lọc theo 
function filterService() {
    const category = document.getElementById("categoryFillter").value;
    const status = document.getElementById("statusFillter").value;
    fetch(`/admin/service/filter_service?category=${encodeURIComponent(category)}&status=${encodeURIComponent(status)}`)
        .then(respone => respone.json())
        .then(data => {
            const tbody = document.getElementById("tableBody")

            tbody.innerHTML = "";

            data.forEach(service => {
                tbody.innerHTML += `
                            <tr id="${service.id}">
                                <td>${service.id}</td>
                                <td>${service.name}</td>
                                <td>${service.category}</td>
                                <td>${service.sale_price}</td>
                                <td>${service.status}</td>
                                <td>
                                    <button type="button" class="btn" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal" onclick="showInforService(${service.id})">
                                        ...
                                    </button>
                                </td>
                            </tr>
                        `;
            });
        })
        .catch(error => { console.log(error); })

}
