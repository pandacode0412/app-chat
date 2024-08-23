import { Alert, Button, Snackbar } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { currentUser, register } from '../../Redux/Auth/Action'
import { green } from '@mui/material/colors';
const Siginup = () => {
    const navigate = useNavigate()
    const [openSnackbar, setOpenSnackbar] = useState(false)
    const [inputData, setInputData] = useState({ full_name: "", email: "", password: "" });
    const { auth } = useSelector(store => store)
    const dispatch = useDispatch()
    const token = localStorage.getItem("token")


    const handleSubmit = (e) => {
        e.preventDefault()
        dispatch(register(inputData))
        setOpenSnackbar(true)
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputData((values) => ({ ...values, [name]: value }))
    }

    const handleSnackbarClose = () => {
        setOpenSnackbar(false)
    }

    useEffect(() => {
        if (token)
            dispatch(currentUser(token))
    }, [token])


    useEffect(() => {
        if (auth.reqUser?.full_name)
            navigate("/")
    }, [auth.reqUser])


    return (
        <div>
            <div>
                <div className='flex flex-col justify-center min-h-screen items-center'>
                    <div className='w-[30%] p-10 shadow-md bg-white'>
                        <form onSubmit={handleSubmit} className='space-y-5'>
                            <div>
                                <p className='mb-2'>User Name</p>
                                <input
                                    className='py-2 px-3 outline outline-green-600 w-full rounded-md border-1'
                                    type='text'
                                    placeholder='Enter username'
                                    name='full_name'
                                    onChange={(e) => handleChange(e)}
                                    value={inputData.full_name}
                                />
                            </div>
                            <div>
                                <p className='mb-2'>Email</p>
                                <input
                                    className='py-2 px-3 outline outline-green-600 w-full rounded-md border-1'
                                    type='text'
                                    placeholder='Enter Email'
                                    name='email'
                                    onChange={(e) => handleChange(e)}
                                    value={inputData.email}
                                />
                            </div>
                            <div>
                                <p className='mb-2'>Password</p>
                                <input
                                    className='py-2 px-3 outline outline-green-600 w-full rounded-md border-1'
                                    type='text'
                                    placeholder='Enter Password'
                                    name='password'
                                    onChange={(e) => handleChange(e)}
                                    value={inputData.password}
                                />
                            </div>
                            <div>
                                <Button
                                    className='w-full'
                                    type='submit'
                                    variant='contained'
                                    sx={{ bgcolor: green[700], padding: ".5rem 0rem" }}
                                >Sign up
                                </Button>
                            </div>
                        </form>
                        <div className='flex space-x-3 items-center mt-5 cursor-pointer '>
                            <p className=''>Already Have Account</p>
                            <Button variant='text' onClick={() => navigate("/signin")}> signin</Button>
                        </div>
                    </div>
                </div>
                <Snackbar
                    onpen={openSnackbar}
                    autoHideDuration={6000}
                    onClose={handleSnackbarClose}
                >
                    <Alert
                        onClose={handleSnackbarClose}
                        security='success'
                        sx={{ width: '100%' }}
                    >
                        Your Account Successfully Created!
                    </Alert>
                </Snackbar>
            </div>
        </div>
    )
}

export default Siginup