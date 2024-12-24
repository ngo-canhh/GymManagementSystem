function addNewRow() {
    const tbody = document.getElementById("tableBody");

    // Tạo một hàng mới
    const newRow = document.createElement("tr");
    newRow.className = "align-middle";
    newRow.id = "new_row";

    // Nội dung của hàng mới
    newRow.innerHTML = `
        <td class="text-center" style="width: 7%;">
            <input class="form-control form-control-sm" type="number" disabled>
        </td>
        <td style="width: 20%;">
            <input class="form-control form-control-sm" type="text" id="name">
        </td>
        <td style="width: 12%;">
            <input class="form-control form-control-sm" type="text" id="brand" required>
        </td>
        <td style="width: 10%;">
            <input class="form-control form-control-sm" type="date" id="buy_date"required>
        </td>
        <td style="width: 8%;">
            <input class="form-control form-control-sm" type="text" id="purchase_price" required>
        </td>
        <td class="text-center" style="width: 5%;">
            <input class="form-control form-control-sm" type="text" id="warranty" required>
        </td>
        <td class="text-center" style="width: 6%;">
            <input class="form-control form-control-sm" type="number" id="quantity" required>
        </td>
       <td style="width: 8%;">
            <select class="form-control form-control-sm" name="status" id="status">
                <option value="Hoạt động" selected>Hoạt động</option>
                <option value="Bảo trì">Bảo trì</option>
            </select>
        <td style="width: 12%;">
            <input class="form-control form-control-sm" type="text" id="position" required>
        </td>
        <td class="text-center" style="width: 10%;">
            <button type="button" class="btn btn-outline-dark btn-sm" onclick="addNewEquipment()">
                Thêm
            </button>
        </td>
    `;

    // Thêm hàng vào bảng
    tbody.appendChild(newRow);
}

function addNewEquipment() {
    const new_equipment = {
        name: document.getElementById("name").value,
        brand: document.getElementById("brand").value,
        buy_date: document.getElementById("buy_date").value,
        purchase_price: document.getElementById("purchase_price").value,
        warranty: document.getElementById("warranty").value,
        quantity: document.getElementById("quantity").value,
        status: document.getElementById("status").value,
        position: document.getElementById("position").value
    }
    for (let key in new_equipment) {
        if (new_equipment[key] === "") {
            alert("Vui lòng điền đầy đủ thông tin cho tường: " + key);
            return;
        }
    }

    fetch(`/admin/equipment/add_new_equipment`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(new_equipment)
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
            const old_row = document.getElementById("new_row");
            if (old_row) {
                old_row.remove();
            }
            const tbody = document.getElementById("tableBody");
            tbody.innerHTML += `
                <tr id="${data.id}" class="align-middle">
                        <!-- Cột ID -->
                        <td class="text-center" style="width: 7%;">
                            <input class="form-control form-control-sm" type="number" value="${data.id}" name="id"
                                disabled>
                        </td>
                        <!-- Cột Name (Dài hơn) -->
                        <td style="width: 20%;">
                            <input class="form-control form-control-sm" type="text" value="${data.name}" name="name">
                        </td>
                        <!-- Cột Brand -->
                        <td style="width: 12%;">
                            <input class="form-control form-control-sm" type="text" value="${data.brand}" name= "brand">
                        </td>
                        <!-- Cột Buy Date -->
                        <td style="width: 10%;">
                            <input class="form-control form-control-sm" type="date" value="${data.buy_date}" name="buy_date">
                        </td>
                        <!-- Cột Purchase Price (Ngắn hơn) -->
                        <td style="width: 8%;">
                            <input class="form-control form-control-sm" type="text" value="${data.purchase_price}" name="purchase_price">
                        </td>
                        <!-- Cột Warranty -->
                        <td class="text-center" style="width: 5%;">
                            <input class="form-control form-control-sm" type="text" value="${data.warranty}" name="warranty">
                        </td>
                        <!-- Cột Quantity -->
                        <td class="text-center" style="width: 6%;">
                            <input class="form-control form-control-sm" type="number" value="${data.quantity}" name="quantity">
                        </td>
                        <!-- Cột Status (Ngắn hơn) -->
                        <td style="width: 8%;">
                            <select class="form-control form-control-sm" name="status" id="">
                                <option value="Hoạt động" ${equipment.status === 'Hoạt động' ? 'selected' : ''}>Hoạt động</option>
                                <option value="Bảo trì" ${equipment.status === 'Bảo trì' ? 'selected' : ''}>Bảo trì</option>
                            </select>
                        </td>
                        <!-- Cột Position -->
                        <td style="width: 12%;">
                            <input class="form-control form-control-sm" type="text" value="${data.position}" name="position">
                        </td>
                        <!-- Nút Update -->
                        <td class="text-center" style="width: 10%;">
                            <button type="button" class="btn btn-outline-dark btn-sm"
                                onclick='updateEquipment(${data.id})'">
                                Update
                            </button>
                        </td>
                    </tr>
            `;
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`ERROR!!!`);
        })
}




function updateEquipment(equipmentID) {
    const row = document.getElementById(equipmentID);
    const equipment = {};
    const inputs = row.querySelectorAll('input, select');

    inputs.forEach((input) => {
        const name = input.getAttribute('name');
        equipment[name] = input.value;
    });

    console.log(equipment);



    fetch(`/admin/equipment/update_equipment`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(equipment),
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
            const update_row = document.getElementById(data.id);
            if (!update_row) {
                alert("Equipment not found");
            } else {
                update_row.cells[1].querySelector('input').value = data.name;
                update_row.cells[2].querySelector('input').value = data.brand;
                update_row.cells[3].querySelector('input').value = data.buy_date;
                update_row.cells[4].querySelector('input').value = data.purchase_price;
                update_row.cells[5].querySelector('input').value = data.warranty;
                update_row.cells[6].querySelector('input').value = data.quantity;
                update_row.cells[7].querySelector('select').value = data.status;
                update_row.cells[8].querySelector('input').value = data.position;
            }
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`ERROR!!!`);
        })
}


function filterEquipment() {
    const filterBrand = document.getElementById("filterBrand").value;
    const filterStatus = document.getElementById("filterStatus").value;
    const filterPosition = document.getElementById("filterPosition").value;

    fetch(`/admin/equipment/filter_equipment?filterBrand=${encodeURIComponent(filterBrand)}&filterStatus=${encodeURIComponent(filterStatus)}&filterPosition=${encodeURIComponent(filterPosition)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                });
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById("tableBody");
            tbody.innerHTML = "";
            data.forEach(equipment => {
                tbody.innerHTML += `
                 <tr id="${equipment.id}" class="align-middle">
                        <!-- Cột ID -->
                        <td class="text-center" style="width: 7%;">
                            <input class="form-control form-control-sm" type="number" value="${equipment.id}" name="id"
                                disabled>
                        </td>
                        <!-- Cột Name (Dài hơn) -->
                        <td style="width: 20%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.name}" name="name">
                        </td>
                        <!-- Cột Brand -->
                        <td style="width: 12%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.brand}" name= "brand">
                        </td>
                        <!-- Cột Buy Date -->
                        <td style="width: 10%;">
                            <input class="form-control form-control-sm" type="date" value="${equipment.buy_date}" name="buy_date">
                        </td>
                        <!-- Cột Purchase Price (Ngắn hơn) -->
                        <td style="width: 8%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.purchase_price}" name="purchase_price">
                        </td>
                        <!-- Cột Warranty -->
                        <td class="text-center" style="width: 5%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.warranty}" name="warranty">
                        </td>
                        <!-- Cột Quantity -->
                        <td class="text-center" style="width: 6%;">
                            <input class="form-control form-control-sm" type="number" value="${equipment.quantity}" name="quantity">
                        </td>
                        <!-- Cột Status (Ngắn hơn) -->
                        <td style="width: 8%;">
                            <select class="form-control form-control-sm" name="status" id="">
                                <option value="Hoạt động" ${equipment.status === 'Hoạt động' ? 'selected' : ''}>Hoạt động</option>
                                <option value="Bảo trì" ${equipment.status === 'Bảo trì' ? 'selected' : ''}>Bảo trì</option>
                            </select>
                        </td>
                        <!-- Cột Position -->
                        <td style="width: 12%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.position}" name="position">
                        </td>
                        <!-- Nút Update -->
                        <td class="text-center" style="width: 10%;">
                            <button type="button" class="btn btn-outline-dark btn-sm"
                                onclick='updateEquipment(${equipment.id})'">
                                Update
                            </button>
                        </td>
                    </tr>
            `;
            })
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`ERROR!!!`);
        })
}

function searchEquipment() {

    const searchInput = document.getElementById("searchInput").value;
    const searchValue = searchInput.trim().toLowerCase();
    fetch(`/admin/equipment/search_equipment?searchValue=${encodeURIComponent(searchValue)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                });
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById("tableBody");
            tbody.innerHTML = ""; // Clear existing table body content
            
            let html = "";
            data.forEach(equipment => {
                html += `
                    <tr id="${equipment.id}" class="align-middle">
                        <!-- Cột ID -->
                        <td class="text-center" style="width: 7%;">
                            <input class="form-control form-control-sm" type="number" value="${equipment.id}" name="id" disabled>
                        </td>
                        <!-- Cột Name -->
                        <td style="width: 20%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.name}" name="name">
                        </td>
                        <!-- Cột Brand -->
                        <td style="width: 12%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.brand}" name="brand">
                        </td>
                        <!-- Cột Buy Date -->
                        <td style="width: 10%;">
                            <input class="form-control form-control-sm" type="date" value="${equipment.buy_date}" name="buy_date">
                        </td>
                        <!-- Cột Purchase Price -->
                        <td style="width: 8%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.purchase_price}" name="purchase_price">
                        </td>
                        <!-- Cột Warranty -->
                        <td class="text-center" style="width: 5%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.warranty}" name="warranty">
                        </td>
                        <!-- Cột Quantity -->
                        <td class="text-center" style="width: 6%;">
                            <input class="form-control form-control-sm" type="number" value="${equipment.quantity}" name="quantity">
                        </td>
                        <!-- Cột Status -->
                        <td style="width: 8%;">
                            <select class="form-control form-control-sm" name="status">
                                <option value="Hoạt động" ${equipment.status === 'Hoạt động' ? 'selected' : ''}>Hoạt động</option>
                                <option value="Bảo trì" ${equipment.status === 'Bảo trì' ? 'selected' : ''}>Bảo trì</option>
                            </select>
                        </td>
                        <!-- Cột Position -->
                        <td style="width: 12%;">
                            <input class="form-control form-control-sm" type="text" value="${equipment.position}" name="position">
                        </td>
                        <!-- Nút Update -->
                        <td class="text-center" style="width: 10%;">
                            <button type="button" class="btn btn-outline-dark btn-sm" onclick="updateEquipment(${equipment.id})">
                                Update
                            </button>
                        </td>
                    </tr>
                `;
            });
            
            // Append all rows at once
            tbody.innerHTML = html;
            
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`ERROR!!!`);
        })
}

