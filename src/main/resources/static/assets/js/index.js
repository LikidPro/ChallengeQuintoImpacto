
const { createApp } = Vue
createApp({
  data() {
    return {
      urlAlumno:"/api/alumno/autenticado",
      alumno:"",

        
    }  
  },
  created() {
    this.alumnoAutenticado(this.urlAlumno)
  }, 
  methods: {
    alumnoAutenticado(url){
      axios.get(url)
      .then(response => {
         this.alumno = response.data
         if(this.alumno ==""){
          console.log("no inicio seccion");
         }else{

           console.log(this.alumno);
         }
         
      })
    },
    logOut(){
      const swalWithBootstrapButtons = Swal.mixin({
          customClass: {
            confirmButton: '#212121',
            // cancelButton: '#212121'
          },
          buttonsStyling: true
        })
         swalWithBootstrapButtons.fire({
          background:'#dc1d1d',
          color:'white',
          title: '¿Seguro quieres cerrar sesion?',
         showCancelButton: false,
          confirmButtonColor: '#0a0a0a',
          confirmButtonText: 'Si', 
          // cancelButtonText: 'No',
          reverseButtons: true
        }).then((result) => {
        if (result.isConfirmed) {
              Swal.fire({
                background:'#dc1d1d',
                  color:'white',
                  position: 'top-center',
                  title: 'Sesion finalizada',
                  showConfirmButton: false,
                  timer: 10500
                })
              axios.post('/api/logout').then(response =>  window.location.href = '/login.html')
          }
        })
   },
  },
  computed:{
    
    }
  
}).mount('#app')