import { Alert, Button, Snackbar } from '@mui/material';
import { green } from '@mui/material/colors';
import React, { useState, useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { login, currentUser } from '../../Redux/Auth/Action';

const Signin = () => {
    const navigate = useNavigate()
    const [openSnackbar, setOpenSnackbar] = useState(false)
    const [inputData, setInputData] = useState({ email: "", password: "" });
    const { auth } = useSelector(store => store)
    console.log("adfasdf", auth)
    const dispatch = useDispatch()
    const token = localStorage.getItem("token")
    const handleSubmit = (e) => {
        e.preventDefault()
        setOpenSnackbar(true)
        console.log("token", token)
        dispatch(login(inputData))

    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputData((values) => ({ ...values, [name]: value }))
    }

    const handleSnackbarClose = () => {
        setOpenSnackbar(false)
    }

    useEffect(() => {
        if (token) dispatch(currentUser(token))


    }, [token])


    useEffect(() => {
        if (auth?.reqUser?.full_name)
            navigate("/")
    }, [auth.reqUser])


    return (
        <div>
            <div className='flex justify-center h-screen items-center'>
                <div className='w-[30%] p-10 shadow-md bg-white'>
                    <form onSubmit={handleSubmit} className='space-y-5'>
                        <div>
                            <p className='mb-2'>Email</p>
                            <input
                                placeholder='Enter your Email'
                                type='text'
                                onChange={handleChange}
                                value={inputData.email}
                                name="email"
                                className='py-2 outline outline-green-600 w-full rounded-md border' />
                        </div>
                        <div>
                            <p className='mb-2'>Password</p>
                            <input
                                placeholder='Enter your Password'
                                type='password'
                                onChange={handleChange}
                                value={inputData.password}
                                name="password"
                                className='py-2 outline outline-green-600 w-full rounded-md border' />
                        </div>
                        <div>
                            <Button
                                className='w-full'
                                type='submit'
                                variant='contained'
                                sx={{ bgcolor: green[700], padding: ".5rem 0rem" }}
                            >
                                Sign In
                            </Button>
                        </div>
                    </form>
                    <div className='flex space-x-3 items-center mt-5'>
                        <p className='m-0'>Create New Account</p>
                        <Button variant='text' onClick={() => navigate("/signup")}> signup</Button>
                    </div>
                </div>

            </div>
            {/* <Snackbar open={handleSnackbarClose} autoHideDuration={6000} onClose={handleSnackbarClose}>
                <Alert
                    onClose={handleSnackbarClose}
                    severity="success"
                    // variant="filled"
                    sx={{ width: '100%' }}
                >
                    This is a success  message
                </Alert>
            </Snackbar> */}
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
                    This is a success  message
                </Alert>
            </Snackbar>
        </div>
    )
}

export default Signin