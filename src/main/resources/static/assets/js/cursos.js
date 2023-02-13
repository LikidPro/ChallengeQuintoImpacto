
const { createApp } = Vue
createApp({
  data() {
    return {
        cursos:[],
        url:"/api/cursos",
        urlAlumno:"/api/alumno/autenticado",
        alumno:"",

        
    }  
  },
  created() {
    this.obtenerData(this.url)
    this.alumnoAutenticado(this.urlAlumno)
  }, 
  methods: {
    obtenerData(url){
        axios.get(url)
         .then(response => {
            this.cursos = response.data
            console.log(this.cursos);
            
         })

    },
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

    
    agregarAlumno(curso){
      console.log(curso.nombre);
      if(this.alumno == ''){
        window.location.href = '/login.html'
      }
      axios.post("/api/curso/inscripcion", "curso=" + curso.nombre).then(response => {

        Swal.fire({
          background:'#dc1d1d',
          position: 'top-end',
          title: response.data,
          showConfirmButton: false,
          timer: 1500
        })}).catch(error =>{
          Swal.fire({
            background:'#dc1d1d',
            position: 'top-end',
            title: error.response.data,
            showConfirmButton: false,
            timer: 1500
          })
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