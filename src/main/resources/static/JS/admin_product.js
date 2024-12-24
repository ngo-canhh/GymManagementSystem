//thêm sản phẩm
function addNewProduct() {
    const new_product = {       
        name :document.getElementById("name").value,
        purchase_price :document.getElementById("purchasePrice").value,
        sale_price :document.getElementById("salePrice").value,
        entry_date :document.getElementById("entryDate").value,
        expiry_date :document.getElementById("expiryDate").value,
        status :document.getElementById("status").value,
        quantity :document.getElementById("quantity").value,
        description :document.getElementById("description").value

    }

    fetch(`/admin/product/add_new_product`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(new_product)
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
            const tbody = document.getElementById("tableBody")
            tbody.innerHTML += `
                            <tr id="${data.id}">
                                <td>${data.id}</td>
                                <td>${data.name}</td>
                                <td>${data.purchase_price}</td>
                                <td>${data.sale_price}</td>
                                <td>${data.quantity}</td>
                                <td>${data.entry_date}</td>
                                <td>${data.expiry_date}</td>
                                <td>${data.status}</td>
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
function showInforProduct(productID) {
    fetch(`/admin/product/productID?productID=${encodeURIComponent(productID)}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(message => {
                    throw new Error(message);
                })
            }
            return response.json();
        })
        .then(service => {
            document.getElementById("productID").value = service.id;
            document.getElementById("productName").value = service.name;
            document.getElementById("productPurchasePrice").value = service.purchase_price;
            document.getElementById("productSalePrice").value = service.sale_price;
            document.getElementById("productEntryDate").value = service.entry_date;
            document.getElementById("productExpiryDate").value = service.expiry_date;
            document.getElementById("productStatus").value = service.status;
            document.getElementById("productQuantity").value = service.quantity;
            document.getElementById("productDescription").value = service.description;
        })
        .catch(error => {
            console.log("Lỗi", error.message);
            alert(`ERROR!!!`);

        })
}

//cập nhật gói tập
function updateService() {
    const product = {
        id: document.getElementById("productID").value,
        name :document.getElementById("productName").value,
        purchase_price :document.getElementById("productPurchasePrice").value,
        sale_price :document.getElementById("productSalePrice").value,
        entry_date :document.getElementById("productEntryDate").value,
        expiry_date :document.getElementById("productExpiryDate").value,
        status :document.getElementById("productStatus").value,
        quantity :document.getElementById("productQuantity").value,
        description :document.getElementById("productDescription").value
    }

    fetch(`/admin/product/upadate_product`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(product),
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
                        <td>${data.name}</td>
                        <td>${data.purchase_price}</td>
                        <td>${data.sale_price}</td>
                        <td>${data.quantity}</td>
                        <td>${data.entry_date}</td>
                        <td>${data.expiry_date}</td>
                        <td>${data.status}</td>
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
            alert(`Cap nhat that bai`);
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

            data.forEach(service => {
                tbody.innerHTML += `
                    <tr id="${service.id}">
                        <td>${service.id}</td>
                        <td>${service.name}</td>
                        <td>${service.purchase_price}</td>
                        <td>${service.sale_price}</td>
                        <td>${service.quantity}</td>
                        <td>${service.entry_date}</td>
                        <td>${service.expiry_date}</td>
                        <td>${service.status}</td>
                        <td>
                            <button type="button" class="btn" data-bs-toggle="modal"
                                data-bs-target="#exampleModal" onclick="showInforProduct(${service.id})">
                                ...
                            </button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(error => {
            console.log("Lỗi: " ,error.message);
        })
});


//lọc theo 
function filteProduct() {
    const entry_date = document.getElementById("filterEntryDate").value;
    const expiry_date = document.getElementById("filterExpiryDate").value;
    const status = document.getElementById("filterStatus").value;
    fetch(`/admin/product/filter_product?entry_date=${encodeURIComponent(entry_date)}&expiry_date=${encodeURIComponent(expiry_date)}&status=${encodeURIComponent(status)}`)
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

            data.forEach(service => {
                tbody.innerHTML += `
                    <tr id="${service.id}">
                        <td>${service.id}</td>
                        <td>${service.name}</td>
                        <td>${service.purchase_price}</td>
                        <td>${service.sale_price}</td>
                        <td>${service.quantity}</td>
                        <td>${service.entry_date}</td>
                        <td>${service.expiry_date}</td>
                        <td>${service.status}</td>
                        <td>
                            <button type="button" class="btn" data-bs-toggle="modal"
                                data-bs-target="#exampleModal" onclick="showInforProduct(${service.id})">
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


