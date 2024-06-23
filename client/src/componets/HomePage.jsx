import React, { useState } from 'react'
import { TbCircleDashed } from "react-icons/tb"
import { BiCommentDetail } from "react-icons/bi"
import { MdSearch } from "react-icons/md";
import { BsEmojiSmile, BsFilter, BsMicFill, BsThreeDotsVertical } from 'react-icons/bs'
import ChatCard from './ChatCard/ChatCard';
import { queries } from '@testing-library/react';
import { LiaUserSlashSolid } from 'react-icons/lia';
import { AiOutlineSearch } from 'react-icons/ai';
import MessageCard from './MessageCard/MessageCard';
import { ImAttachment } from 'react-icons/im';
import './HomePage.css'
import { useNavigate } from 'react-router-dom';
import Profile from './Profile/Profile';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import CreateGroup from './Group/CreateGroup';

const HomePage = () => {

    const [querys, setQuerys] = useState(null)
    const [currentChat, setCurrentChat] = useState(null)
    const [content, setContent] = useState("")
    const navigate = useNavigate()
    const [isGroup, setIsGroup] = useState(false)
    const handleClickOnChatCard = () => {
        setCurrentChat(true)
    }

    const handleSearch = () => {

    }

    const handleCreateNewMessage = () => {

    }

    const [isProfile, setIsProfile] = useState(false)

    const handleNavigate = () => {

        // navigate("/profile")
        setIsProfile(true)
    }

    const handleCloseOpenProfile = () => {
        setIsProfile(false)
    }

    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (e) => {
        setAnchorEl(e.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleCreateGroup = () => {
        setIsGroup(true)
    }

    return (
        <div className='relative '>
            <div className='w-full py-14 bg-[#00a884] '>  </div>
            <div className='flex bg-[#f0f2f5] h-[90vh] absolute  left-[2vw] top-[5vh] w-[96vw]'>
                <div className='left w-[30%] bg-[#e8e9ec] h-full'>
                    {/* profile */}
                    {isGroup && <CreateGroup />}
                    {isProfile && <div className='w-full h-full'>
                        <Profile handleCloseOpenProfile={handleCloseOpenProfile} />
                    </div>}
                    {!isProfile && !isGroup && <div className='w-full'>

                        {/* home */}
                        {<div className='flex justify-between items-center p-3'>
                            <div onClick={handleNavigate} className='flex items-center space-x-3'>
                                <img className='rounded-full w-10 h-10 cursor-pointer'
                                    alt=''
                                    src='https://cdn.pixabay.com/photo/2024/02/02/03/42/seagull-8547189_960_720.png' />
                                <p>user</p>
                            </div>
                            <div className='space-x-3 text-2xl flex'>
                                <TbCircleDashed className='cursor-pointer' onClick={() => navigate("/status")} />
                                <BiCommentDetail />
                                <div>


                                    <BsThreeDotsVertical

                                        id="basic-button"
                                        aria-controls={open ? 'basic-menu' : undefined}
                                        aria-haspopup="true"
                                        aria-expanded={open ? 'true' : undefined}
                                        onClick={handleClick}
                                    />

                                    <Menu
                                        id="basic-menu"
                                        anchorEl={anchorEl}
                                        open={open}
                                        onClose={handleClose}
                                        MenuListProps={{
                                            'aria-labelledby': 'basic-button',
                                        }}
                                    >
                                        <MenuItem onClick={handleClose}>Profile</MenuItem>
                                        <MenuItem onClick={handleCreateGroup}>Create Group</MenuItem>
                                        <MenuItem onClick={handleClose}>Logout</MenuItem>
                                    </Menu>
                                </div>
                            </div>
                        </div>}

                        <div className='relative flex justify-center items-center bg-white py-4 px-3'>
                            <input className='border-none out-line-none bg-slate-200 rounded-md w-[93%] pl-9 py-2'
                                type='text'
                                placeholder='Search or start new Chat'
                                onChange={(e) => {
                                    setQuerys(e.target.value)
                                    handleSearch(e.target.value)
                                }}
                                value={querys}
                            />
                            <MdSearch className='left-5 top-7 absolute' />
                            <div>
                                <BsFilter className='ml-4 text-3xl' />
                            </div>
                        </div>
                        {/* all user */}
                        <div className='bg-white overflow-y-scroll h-[72vh] px-3'>
                            {
                                querys && [1, 1, 1, 1, 1].map((item) => (
                                    <div onClick={handleClickOnChatCard}>

                                        <hr />
                                        <ChatCard />
                                    </div>))
                            }
                        </div>
                        {/* default whats up page page */}

                        <div>

                        </div>
                    </div>}
                </div>



                {!currentChat && <div className='w-[70%] flex flex-col items-center justify-center h-full'>
                    <div className=' max-w-[70%]  text-center'>
                        <img
                            src='https://res.cloudinary.com/zarmariya/image/upload/v1662264838/whatsapp_multi_device_support_update_image_1636207150180-removebg-preview_jgyy3t.png'
                            alt=''
                        />
                        <h1 className='text-4xl text-gray-600'>WhatsApp web</h1>
                        <p className='my-9'>send and reveive message without keeping your phone online . Use WhatsApp on Up to 4 Linked devices and phone at the same time</p>
                    </div>
                </div>}
                {/* message */}
                {
                    currentChat && <div className='w-[70%] relative bg-blue-200'>
                        <div className='header absolute top-0 w-full bg-[#f0f2f5]'>
                            <div className='flex justify-between'>
                                <div className='py-3 space-x-4 flex items-center px-3'>
                                    <img className="w-10 h-10 rounded-full" src='https://cdn.pixabay.com/photo/2023/09/24/16/31/beetle-8273349_640.jpg' alt='' />
                                    <p>
                                        username
                                    </p>
                                </div>
                                <div className='py-3 flex space-x-4 items-center px-3'>
                                    <AiOutlineSearch />
                                    <BsThreeDotsVertical />

                                </div>
                            </div>
                        </div>
                        {/* message section */}
                        <div className='px-10 h-[85vh] overflow-y-scroll '>
                            <div className='space-y-1 flex flex-col justify-center  mt-20 py-2'>
                                {
                                    [1, 1, 1, 1, 1].map((item, i) => <MessageCard isReqUserMessage={i % 2 === 0} content={"message"} />)
                                }
                            </div>
                        </div>
                        {/* footer part */}
                        <div className='footer bg-[#f0f2f5] absolute bottom-0 w-full py-3 text-2xl'>
                            <div className='flex justify-between items-center px-5 relative' >
                                <BsEmojiSmile className='cursor-pointer' />
                                <ImAttachment />

                                <input
                                    className='py-2 outline-none border-none bg-white pl-4 rounded-md w-[85%]'
                                    type='text'
                                    onChange={(e) => setContent(e.target.value)}
                                    value={content}
                                    onKeyPress={(e) => {
                                        if (e.key == "Enter") {
                                            handleCreateNewMessage();
                                            setContent("")
                                        }
                                    }}
                                />
                                <BsMicFill />
                            </div>
                        </div>
                    </div>

                }
            </div>
        </div>

    )
}

export default HomePage