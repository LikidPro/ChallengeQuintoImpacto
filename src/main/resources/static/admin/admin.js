
const { createApp } = Vue
createApp({
  data() {
    return {
        alumnosUrl:"/api/alumnos",
        cursosUrl:"/api/cursos",
        profesoresUrl:"/api/profesores",
        alumnos:[],
        alumnosFiltrados:[],
        cursos:[],
        cursosNombres:[],
        profesores:[],
        alumnoSeleccionado:'',
        cursoSeleccionado:'',
        profesorSeleccionado:'',
        alumnoNombre:'',
        alumnoApellido:'',
        alumnoEmail:'',
        profesorNombre:'',
        profesorApellido:'',
        cursoNombre:'',
        cursoArea:'',
        cursoHorario:'',
        cursoProfesor:'',
        alumnoBuscado:'',
        checkFiltrado:[],
        cursoBuscado:[],
        
    }  
  },
  created() {
    this.obtenerAlumnos(this.alumnosUrl)
    this.obtenerCursos(this.cursosUrl)
    this.obtenerProfesores(this.profesoresUrl)
    
  }, 
  methods: {
    obtenerAlumnos(URL){
        axios.get(URL)
        .then(response => {
            this.alumnos = response.data
            this.alumnosFiltrados = this.alumnos
            console.log(this.alumnos);
           
          })
       
    },
    obtenerCursos(URL){
        axios.get(URL)
        .then(response => {
            this.cursos = response.data
            this.cursos.forEach(curso => {
              
              if(!this.cursosNombres.includes(curso.nombre)){
                this.cursosNombres.push(curso.nombre)
              }
            });
            console.log(this.cursos);
            console.log(this.cursosNombres);
           
          })


    },
    obtenerProfesores(URL){
        axios.get(URL)
        .then(response => {
            this.profesores = response.data
            console.log(this.profesores);
          })
       
    },
    mostrarTabla(tabla){
        let alumnos = document.getElementById('alumnos')
        let profesores = document.getElementById('profesores')
        let cursos = document.getElementById('cursos')
        let botonalumnos = document.getElementById('botonalumnos')
        let botonprofesores = document.getElementById('botonprofesores')
        let botoncursos = document.getElementById('botoncursos')
        if(tabla == "alumnos"){
            alumnos.classList.remove("sin-display")
            profesores.classList.add("sin-display")
            cursos.classList.add("sin-display")
            botonalumnos.classList.add("activeb")
            botonprofesores.classList.remove("activeb")
            botoncursos.classList.remove("activeb")
        }
        if(tabla == "profesores"){
            alumnos.classList.add("sin-display")
            profesores.classList.remove("sin-display")
            cursos.classList.add("sin-display")
            botonalumnos.classList.remove("activeb")
            botonprofesores.classList.add("activeb")
            botoncursos.classList.remove("activeb")
        }
        if(tabla == "cursos"){
            alumnos.classList.add("sin-display")
            profesores.classList.add("sin-display")
            cursos.classList.remove("sin-display")
            botonalumnos.classList.remove("activeb")
            botonprofesores.classList.remove("activeb")
            botoncursos.classList.add("activeb")
        }
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
   editarAlumno(id){
    this.alumnoSeleccionado = this.alumnos.find(alumno => alumno.id == id)
    
   },
   editarCurso(id){
    this.cursoSeleccionado = this.cursos.find(curso => curso.id == id)
   },
   editarProfesor(id){
    this.profesorSeleccionado = this.profesores.find(profesor => profesor.id == id)
   },
   cambiarAlumno(){
    if(this.alumnoNombre != ''){
      axios.patch('/api/alumno/nombre',  'email=' + this.alumnoSeleccionado.email + '&nombre=' + this.alumnoNombre).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    }
    
    if(this.alumnoApellido != ''){
      axios.patch('/api/alumno/apellido', 'email=' + this.alumnoSeleccionado.email + '&apellido=' + this.alumnoApellido).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    
    }

    if(this.alumnoEmail != ''){
      axios.patch('/api/alumno/email', 'email=' + this.alumnoSeleccionado.email + '&email2=' +  this.alumnoEmail ).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    }
    Swal.fire({
      background:'#dc1d1d',
      position: 'top-end',
      title: 'Alumno cambiado con exito',
      showConfirmButton: false,
      timer: 1500
    })
    this.obtenerAlumnos(this.alumnosUrl)

   },
   cambiarCurso(){
    
    if(this.cursoNombre != ''){
      axios.patch('/api/curso/nombre',  'nombre=' + this.cursoSeleccionado.nombre + '&nombre=' + this.cursoNombre).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    }
    
    if(this.cursoArea != ''){
      axios.patch('/api/curso/area', 'nombre=' + this.cursoSeleccionado.nombre + '&area=' + this.cursoArea).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    
    }

    if(this.cursoHorario != ''){
      axios.patch('/api/curso/horario', 'nombre=' + this.cursoSeleccionado.nombre + '&horario=' +  this.cursoHorario ).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    }
    if(this.cursoProfesor != ''){
      axios.patch('/api/curso/profesor', 'nombre=' + this.cursoSeleccionado.nombre + '&profesorId=' +  this.cursoProfesor ).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    }
    Swal.fire({
      background:'#dc1d1d',
      position: 'top-end',
      title: 'Curso cambiado con exito',
      showConfirmButton: false,
      timer: 1500
    })
    this.obtenerCursos(this.cursosUrl)

   },
   cambiarProfesor(){
    console.log(this.profesorSeleccionado.id);
    
    if(this.profesorNombre != ''){
      console.log("hola");
      axios.patch('/api/profesor/nombre',  'id=' + this.profesorSeleccionado.id + '&nombre=' + this.profesorNombre).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    }
    
    if(this.profesorApellido != ''){
      axios.patch('/api/profesor/apellido', 'id=' + this.profesorSeleccionado.id + '&apellido=' + this.profesorApellido).then(response =>{
        console.log(response.data);
         })
         .catch(error => this.error = error.response.status);
    
    }

    Swal.fire({
      background:'#dc1d1d',
      position: 'top-end',
      title: 'Profesor cambiado con exito',
      showConfirmButton: false,
      timer: 1500
    })
    this.obtenerProfesores(this.profesoresUrl)

   },
   quitaCursoAlumno(c){
    axios.patch('/api/alumno/curso',  'email=' + this.alumnoSeleccionado.email + '&curso=' + c).then(response =>{
      Swal.fire({
        background:'#dc1d1d',
        position: 'top-end',
        title: 'Alumno cambiado con exito',
        showConfirmButton: false,
        timer: 1500
      }).then(response => {
        this.obtenerAlumnos(this.alumnosUrl)
       }).catch(error => this.error = error.response.status);
    })
   },
   quitarCursoProfesor(){
    Swal.fire({
      background:'#dc1d1d',
      position: 'top-end',
      title: 'Funcion aun no agregada',
      showConfirmButton: false,
      timer: 1500
    }).then(response => {
      this.obtenerProfesores(this.profesoresUrl)
     }).catch(error => this.error = error.response.status);
  
   },
   borrarProfesor(curso){
    axios.patch('/api/curso/profesor/borrar', 'nombre=' + curso).then(response => {
      Swal.fire({
        background:'#dc1d1d',
        position: 'top-end',
        title: 'Profesor desasignado con exito',
        showConfirmButton: false,
        timer: 1500
      }).then(response => {
        this.obtenerCursos(this.cursosUrl)
       }).catch(error => this.error = error.response.status)
    } )

   },
   eliminarAlumno(id){
    axios.delete('/api/alumno/id')
   },
   crearCurso(){

    axios.post('/api/curso/nuevo', 'nombre=' + this.cursoNombre + '&area=' + this.cursoArea + '&horario=' + this.cursoHorario.toUpperCase()+ '&profesorId=' + this.cursoProfesor).then(response => console.log(response.data))
   },
   crearProfesor(){
    axios.post('/api/profesor/nuevo', 'nombre=' + this.profesorNombre + '&apellido=' + this.profesorApellido ).then(response => console.log(response.data))


   }
  },
  computed:{
  
    filtroDeAlumnos(){
      let primerFiltro = this.alumnosFiltrados.filter(alumno => alumno.nombre.toLowerCase().includes(this.alumnoBuscado.toLowerCase()))
      let segundoFiltro =[]
      if(this.cursoBuscado.length){
        console.log(this.cursoBuscado);
      this.cursoBuscado.forEach(curso => {
         primerFiltro.forEach(alumno => {
          let cursos = [];
          alumno.cursos.forEach(c => {

            if(!cursos.includes(c.curso)){
              cursos.push(c.curso)
              
            }
          })
        
          if(cursos.includes(curso) && !segundoFiltro.includes(alumno)){

            segundoFiltro.push(alumno)
          }

         })
      })
      this.alumnos= segundoFiltro
    }else{
      this.alumnos = primerFiltro
    }
      
    },

    
    }
  
}).mount('#app')