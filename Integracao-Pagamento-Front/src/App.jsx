import { useState } from 'react'
import { Route,Routes } from 'react-router-dom'
import Register from './components/Register'
import Home from './components/Home'
import Login from './components/Login'
import NavigateToRegister from './components/NavigateToRegister'
import ActiveAccount from './components/ActiveAccount'
function App() {
  return (
    <Routes>
      <Route path='*' element={<NavigateToRegister/>}></Route>
      <Route path='verify/active-account/:id' element={<ActiveAccount/>}></Route>
      <Route path='/' element={<Home/>}></Route>
      <Route path='/register' element={<Register/>}></Route>
      <Route path='/login' element={<Login/>}></Route>
    </Routes>
  )
}

export default App
