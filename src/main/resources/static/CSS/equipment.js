const equipments = [
    { id: 1, name: 'Treadmill', brand: 'Life Fitness', model: '95Ti', serial_number: '123456', date_of_purchase: '2023-01-15', is_broken: false, is_missing: false, replacement_cost: 3000 , reason_for_failure: null,  image: 'https://th.bing.com/th/id/OIP.zUf1s3hTfXG2I2mJ1N20wgHaEK?rs=1&pid=ImgDetMain'},
    { id: 2, name: 'Elliptical', brand: 'Precor', model: 'EFX 835', serial_number: '789012', date_of_purchase: '2023-02-20', is_broken: true, is_missing: false, replacement_cost: 2500, reason_for_failure: 'Broken Display' ,  image:'https://i.ebayimg.com/images/g/e4wAAOSwtKxh1v96/s-l1600.jpg'},
    { id: 3, name: 'Bench press', brand: 'Powertec', model: 'WB-PR', serial_number: '345678', date_of_purchase: '2023-03-01', is_broken: false, is_missing: true, replacement_cost: 1200, reason_for_failure: null,  image:'https://th.bing.com/th/id/OIP.M8pL6IeHkY6L7yTq0Lw9fQHaHa?rs=1&pid=ImgDetMain' },
   { id: 4, name: 'Dumbbells Set', brand: 'Bowflex', model: 'SelectTech', serial_number: '901234', date_of_purchase: '2023-03-10', is_broken: false, is_missing: false, replacement_cost: 800 ,reason_for_failure: null,  image: 'https://th.bing.com/th/id/OIP.n6gZ6xS-Zf4hV3k-J2e8eAHaHa?rs=1&pid=ImgDetMain'},
    ];
    let nextEquipmentId = 5;

const equipmentTable = document.getElementById('equipment-list');


function loadEquipments(filter) {
   let filteredEquipments = [...equipments];

    if(filter == 'broken'){
          filteredEquipments = equipments.filter((eq)=>eq.is_broken === true)
     } else if (filter == 'missing'){
            filteredEquipments = equipments.filter((eq)=>eq.is_missing === true)
    }

  equipmentTable.innerHTML = '';
  if (filteredEquipments.length == 0){
      equipmentTable.innerHTML = `<tr><td colspan="9"  style="padding: 8px; border: 1px solid #ddd; text-align: center">No data</td></tr>`;
     }else {

           filteredEquipments.forEach((equipment,index)=>{
             equipmentTable.innerHTML += createEquipmentRow(equipment,index)
       })

      }


      updateCounter()

 }


 function updateCounter() {
    const totalCount = equipments.length;
     const brokenCount = equipments.filter(item=> item.is_broken == true).length
  const missingCount = equipments.filter(item=>item.is_missing == true).length

    document.getElementById('total-count').textContent = totalCount;
      document.getElementById('broken-count').textContent = brokenCount;
  document.getElementById('missing-count').textContent = missingCount;


}

function filterEquipments(status) {
      let  btns  =  document.querySelectorAll('.filter-status .btn');
      btns.forEach((btn) => {
           if (btn.classList.contains('active')) {
               btn.classList.remove('active');
         }
    });


   let currentBtn = document.querySelector(`.filter-status button:nth-child(${status === 'broken' ? 2 : status === 'missing' ? 3 : 1})`);
  currentBtn.classList.add('active');
       loadEquipments(status);
  }




 function createEquipmentRow(equipment, index) {

   let statusBadge = '';
    if (equipment.is_broken) {
           statusBadge = '<span class="badge bg-danger">Broken</span>';
     } else if (equipment.is_missing) {
        statusBadge = '<span class="badge bg-warning text-dark">Missing</span>';
       } else {
            statusBadge = '<span class="badge bg-success">Available</span>';
     }
 let  markBrokenBtn  = equipment.is_broken || equipment.is_missing ? `<button onclick="markAsAvailable(${equipment.id})"  class="btn  btn-sm" style="background-color: #28a745;">Mark as available</button>` : `<button  class="btn  btn-sm " style="background-color: #8b8b8b;"   onclick="markAsBroken(${equipment.id})">Mark as broken</button>` ;
    let  markMissingBtn  =   equipment.is_broken || equipment.is_missing  ? '': ` <button onclick="markAsMissing(${equipment.id})" style="background-color:#d6ba40;"   class="btn  btn-sm" >Mark as Missing</button>`

  return  `<tr  style="border-bottom: 1px solid #ddd;" >
        <td  style="padding: 8px; border: 1px solid #ddd;">${index +1}</td>
            <td  style="padding: 8px; border: 1px solid #ddd;">  <img style="width: 50px; border-radius: 50%;height:50px; object-fit: cover"  src="${equipment.image}" alt="${equipment.name}" /></td>
              <td style="padding: 8px; border: 1px solid #ddd;">${equipment.name}</td>
            <td  style="padding: 8px; border: 1px solid #ddd;">${equipment.brand || ''}</td>
          <td  style="padding: 8px; border: 1px solid #ddd;">${equipment.model || ''}</td>
        <td style="padding: 8px; border: 1px solid #ddd;">${equipment.serial_number || ''}</td>
          <td style="padding: 8px; border: 1px solid #ddd;">${equipment.date_of_purchase || ''}</td>
       <td style="padding: 8px; border: 1px solid #ddd; text-align: left">${statusBadge}</td>
         <td style="padding: 8px; border: 1px solid #ddd; text-align: left">$${equipment.replacement_cost}</td>

     <td  style="padding: 8px; border: 1px solid #ddd;">
            <a style="background-color:#4e4eff;"   class="btn  btn-sm"  href="form.html?id=${equipment.id}">Edit</a>
              <button  class="btn  btn-sm" onclick="deleteEquipment(${equipment.id})" style="background-color: #da273e;">Delete</button>
          ${markBrokenBtn}
               ${markMissingBtn}

           </td>

        </tr>`
  }


function deleteEquipment(id){
      if(confirm('Do you really want to delete this equipment ?')) {
           const index = equipments.findIndex(item => item.id == id);
           if(index !== -1) {
            equipments.splice(index,1);
            loadEquipments();
             }

        }


  }

   function markAsBroken(id) {
  const equipment = equipments.find(item=> item.id == id)
     if(equipment){
            equipment.is_broken = true;
           equipment.is_missing = false
         loadEquipments();
           alert('Thiết bị đã được đánh dấu là hỏng!')
       }

}

 function markAsMissing(id){
      const equipment = equipments.find(item=>item.id == id);
   if(equipment){
      equipment.is_missing = true;
           equipment.is_broken = false
             loadEquipments();
           alert('Thiết bị đã được đánh dấu là thiếu!');

      }

 }


function markAsAvailable(id){
        const equipment = equipments.find(item=>item.id == id)
       if (equipment){
           equipment.is_broken = false
            equipment.is_missing = false;

            loadEquipments();
         alert('Thiết bị đã có sẵn')
      }


 }




 async function saveEquipment(event) {

        event.preventDefault();
   let id =  document.querySelector('form #id') ?  document.querySelector('form #id').value :  0;
        let name = document.querySelector('form [name=name]').value;
   if(!name) {
             alert('Vui lòng điền đầy đủ thông tin có dấu *');
      return;
   }


          let brand = document.querySelector('form [name=brand]').value
    let model  = document.querySelector('form [name=model]').value;
       let serial  = document.querySelector('form [name=serial_number]').value;
          let datePurchase  = document.querySelector('form [name=date_of_purchase]').value;
          let reason =  document.querySelector('form [name=reason_for_failure]').value
        let isBroken  = document.querySelector('form input[name=is_broken]').checked
     let isMissing =  document.querySelector('form input[name=is_missing]').checked

        let cost = Number(document.querySelector('form [name=replacement_cost]').value || 0)

  const fileInput = document.getElementById('image-input');
    let imageURL = null
        if(fileInput && fileInput.files && fileInput.files[0]){
              const file = fileInput.files[0]
            //giả sử  link  sẽ giống như url upload lên (code demo cho nhanh)
          imageURL = "https://source.unsplash.com/200x200/?"+ file.name.split(".")[0]

        }
   let equipmentData = {id : id || nextEquipmentId++ , name: name, brand : brand, model: model,
           serial_number: serial,  date_of_purchase:datePurchase, is_missing:isMissing, is_broken : isBroken ,replacement_cost: cost,  reason_for_failure : reason,
               image: imageURL

         };

       if (id == 0 ){

            equipments.push(equipmentData)


          } else {
             const index  = equipments.findIndex(item=>item.id == id);
          if(index !== -1){
               equipments[index] = {...equipmentData};

          }


        }
            alert('Lưu dữ liệu thành công')

        window.location.href="index.html";

 }


async function loadEditForm(id) {
        const equipment = equipments.find(item =>item.id == id);
  if(equipment){

       document.querySelector('form #id').value = equipment.id;
            document.querySelector('form [name=name]').value = equipment.name
     document.querySelector('form [name=brand]').value = equipment.brand || ''
    document.querySelector('form [name=model]').value  = equipment.model || ''
      document.querySelector('form [name=serial_number]').value  = equipment.serial_number || ''
            document.querySelector('form [name=date_of_purchase]').value = equipment.date_of_purchase || ''
    document.querySelector('form [name=reason_for_failure]').value  = equipment.reason_for_failure || '';

           if(equipment.is_broken){
               document.querySelector('form #isBroken').checked = true;
              }

           if(equipment.is_missing){
               document.querySelector('form #isMissing').checked = true
           }


           document.querySelector('form [name=replacement_cost]').value =  equipment.replacement_cost ;
          }else{
           window.location.href="index.html";

         }



 }