//Thong tin toan bo goi tap
// document.getElementById("services-grid")(function (event) {
//     fetch(`/list_service`)
//     .then(respone => respone.json())
//     .then(data => {
//         document.getElementById("services-grid").innerHTML = "";
//         data.forEach(service => {
//             document.getElementById("services-grid").innerHTML += `
//                 <div class="card">
//                     <div class="card-body">
//                         <h5 class="card-title">${service.name}</h5>
//                         <p class="card-text">${service.description}</p>
//                         <a href="#" class="btn btn-primary">Go somewhere</a>
//                     </div> 
//                 </div>
//             `;
//         })
//     })
//     .catch(error => {
//         console.log(error);
//     });
// });

// document.addEventListener("DOMContentLoaded", function () {
//     document.querySelectorAll('.cate_info').forEach(button =>{
//         button.addEventListener('click', function(){
//             const cateName = this.getAttribute('data-id');
//             const cateName_endcode = encodeURIComponent(cateName);
//             fetch(`/list_service?categoy=${cateName_endcode}`)
//             .then(response => response.json())
//             .then(data =>{
//                 const servicesGrid = document.getElementById("services-grid");
//                 servicesGrid.innerHTML = "";
//                 let html = '<h2>Chung toi cung cac dich vu</h2>';
//                 data.forEach(service =>{
//                     html += `
//                     <div class="col-md-3 mt-2 all-cate" id="all_service">
//                     <div class="card">
//                         <img src="/images/boxing.png" alt="Boxing">
//                         <h3 class="text-center"><b><span style="justify-content: center;">${service.name}</span></b></h3>
//                         <p><span>${service.description}</span></p>
//                         // <a href="/service_detail(id=${service.ID})" class="btn btn-primary">Xem chi tiết</a>
//                         <button class="btn btn-primary" data-id="${service.category}">Xem chi tiet js</button>
//                     </div>
//                 </div>`
//                 });
//                 servicesGrid.innerHTML = html;
            

//             })
//         })
//     })
// });

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll('.cate_info').forEach(button =>{
        button.addEventListener('click', function(){
            const cateName = this.getAttribute('data-id');
            const cateName_endcode = encodeURIComponent(cateName);
            
            console.log('Fetching with category:', cateName);
            console.log('Encoded category:', cateName_endcode);
            
            fetch(`/list_service?category=${cateName_endcode}`)
            .then(response => {
                console.log('Response status:', response.status);
                return response.json();
            })
            .then(data =>{
                console.log('Received data:', data);
                
                const servicesGrid = document.getElementById("services-grid");
                servicesGrid.innerHTML = "";
                
                data.forEach(service =>{
                        servicesGrid.innerHTML += `
                        <div class="col-md-3 mt-2 all-cate" id="all_service">
                            <div class="card">
                                <img src="/images/boxing.png" alt="Boxing">
                                <h3 class="text-center"><b><span style="justify-content: center;">${service.name}</span></b></h3>
                                <p><span>${service.description}</span></p>
                                <a class="btn btn-primary" href="/service_detail?id=${service.id}">Xem chi tiết</a>
                            </div>
                        </div>`;
                    });
                
                
                
            })
            .catch(error => {
                console.error('Error fetching services:', error);
            });
        });
    });
});