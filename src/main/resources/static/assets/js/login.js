
const app = Vue.createApp({
    data()  {
      return {
        password:"",
        email:"",
        contraseña:"",
        text:"",
        type:"",
        primerNombre:"",
        apellido:"",
        emailRegistro:"",
        contraseñaRegistro:"",
        activationCode:null,
       
      }
     
    },
    
  
    methods: {
   
      cambiarForm(){
        let form = document.getElementById("pills-home")
        let form2 = document.getElementById("pills-profile")
        form.classList.remove("active")
        form.classList.remove("show")
        form2.classList.add("active","show")
    },
  
     cambiarForm2(){
        let form2 = document.getElementById("pills-home")
        let form = document.getElementById("pills-profile")
        form.classList.remove("active")
        form.classList.remove("show")
        form2.classList.add("active","show")
    },
    mostrarContra(){
      let tipo = document.getElementById("password");
      if (tipo.type == 'password'){
          tipo.type = 'text';
      }else{
          tipo.type = 'password';
      }
  },
  mostrarContra2(){
      let tipo = document.getElementById("pasword");
      if (tipo.type == 'password'){
          tipo.type = 'text';
      }else{
          tipo.type = 'password';
      }
  },
  mostrarContra3(){
      let tipo = document.getElementById("paword");
      if (tipo.type == 'password'){
          tipo.type = 'text';
      }else{
          tipo.type = 'password';
      }
  },
  ingresarUsuario(){
    if(this.email != "" && this.contraseña != ""){
      axios.post('/api/login', "email="+this.email + "&contraseña=" + this.contraseña ).then(response => console.log(response))
      Swal.fire({
        background:'#dc1d1d',
        position: 'top-end',
        title: 'Bienvenido a Quinto Impacto',
        showConfirmButton: false,
        timer: 1500
      }).then(response => {
        window.history.back()
       }).catch(error => this.error = error.response.status);
       
  } 
  
  },
  crearCliente(){
    let primerNombreRegistro= document.getElementById('primerNombreRegistro');
    let apellidoRegistro= document.getElementById('apellidoRegistro');
    let emailRegistro= document.getElementById('emailRegistro');
    let contraseñaRegistro= document.getElementById('contraseñaRegistro');
    let formRegisto= document.getElementById('formRegistro')
    let formCodigo= document.getElementById('formCodigo')
    primerNombreRegistro.innerText =  ''
    apellidoRegistro.innerText =  ''
    emailRegistro.innerText =  ''
    contraseñaRegistro.innerText =  ''
    if(this.primerNombre == ''){
      primerNombreRegistro.innerText = 'Ingrese su nombre'
    }
    if(this.apellido == ''){
      apellidoRegistro.innerText = 'Ingrese su apellido'
    }
    if(this.emailRegistro == ''){
      emailRegistro.innerText = 'Ingrese su email'
    }
    if(this.contraseñaRegistro == ''){
      contraseñaRegistro.innerText = 'Ingrese una contraseña'
    }
    if(this.primerNombre != '' , this.apellido != '' , this.emailRegistro != '', this.contraseñaRegistro != ''){
    axios.post('/api/alumnos', "nombre=" + this.primerNombre + "&apellido=" + this.apellido + "&email=" + this.emailRegistro + "&contraseña=" +this.contraseñaRegistro).then(response =>{
      this.mandarEmail(response.data)
      console.log(response.data);
    }) 
    
    formRegisto.classList.add('disabled')
    formCodigo.classList.remove('disabled')
    
    }
   
  },
  mandarEmail(response){
    axios.post("/api/alumno/mandar/email" , "to=" + this.emailRegistro + "&code=" + response).then(response => console.log(response.data))
    
  },
  
  validarMail(){
    axios.patch("/api/alumno/validacion", "email=" + this.emailRegistro + "&code=" + this.activationCode).then(response => {
      Swal.fire(response.data)
      let formRegisto= document.getElementById('formRegistro')
      let formCodigo= document.getElementById('formCodigo')
      formRegisto.classList.remove('disabled')
      formCodigo.classList.add('disabled')
    }).catch(error => Swal.fire(error.data))
  },
  
  
    },
    computed:{
  
    }
  }).mount('#app')