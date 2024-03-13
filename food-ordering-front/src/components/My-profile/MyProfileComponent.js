import React, { useState, useEffect } from "react";
import UserService from "../../services/UserService";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";
import "./MyProfileComponent.css";
import EditMyProfileComponent from "./EditMyProfileComponent";
import { Modal, Button } from "react-bootstrap";
import EditPasswordComponent from "./EditPasswordComponent";

const MyProfileComponent = () => {
  const [id, setId] = useState(null);

  const [firstName, setFirstName] = useState("");
  const [firstNameEdit, setFirstNameEdit] = useState("");

  const [lastName, setLastName] = useState("");
  const [lastNameEdit, setLastNameEdit] = useState("");

  const [username, setUsername] = useState("");
  const [usernameEdit, setUsernameEdit] = useState("");

  const [email, setEmail] = useState("");
  const [emailEdit, setEmailEdit] = useState("");

  const [phoneNumber, setPhoneNumber] = useState("");
  const [phoneNumberEdit, setPhoneNumberEdit] = useState("");

  const [password, setPassword] = useState("");
  const [passwordEdit, setPasswordEdit] = useState("");

  const [address, setAddress] = useState("");
  const [addressEdit, setAddressEdit] = useState("");

  const [role, setRole] = useState("");

  const user = {
    firstNameEdit,
    setFirstNameEdit,
    lastNameEdit,
    setLastNameEdit,
    usernameEdit,
    setUsernameEdit,
    emailEdit,
    setEmailEdit,
    phoneNumberEdit,
    setPhoneNumberEdit,
    addressEdit,
    setAddressEdit,
  };
  const userEdit = {
    id,
    firstName: firstNameEdit,
    lastName: lastNameEdit,
    username: usernameEdit,
    email: emailEdit,
    phoneNumber: phoneNumberEdit,
    address: addressEdit,
  };

  const [show, setShow] = useState(false);
  const [showPassModal, setShowPassModal] = useState(false);

  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");

  let passwordObj = {
    oldPassword,
    newPassword,
    setNewPassword,
    setOldPassword,
  };

  useEffect(() => {
    getCurrentUser();
  }, []);

  const handleClose = () => {
    setShow(false);
  };

  const handleClosePassModal = () => {
    setShowPassModal(false);
    setOldPassword("");
    setNewPassword("");
  };

  const handleShowPassModal = () => {
    setShowPassModal(true);
    setPasswordEdit(password);
  };

  const handleShow = () => {
    setShow(true);
    setFirstNameEdit(firstName);
    setLastNameEdit(lastName);
    setEmailEdit(email);
    setPhoneNumberEdit(phoneNumber);
    // setPasswordEdit(password);
    setUsernameEdit(username);
    setAddressEdit(address);
  };

  const getCurrentUser = () => {
    UserService.getCurrentUser()
      .then((response) => {
        setId(response.data.id);
        setFirstName(response.data.firstName);
        setLastName(response.data.lastName);
        setEmail(response.data.email);
        setPhoneNumber(response.data.phoneNumber);
        setUsername(response.data.username);
        // setPassword(response.data.password);
        setAddress(response.data.address);
        setRole(response.data.role);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleSubmitChangePass = () => {
    //validacija unosa
    if (oldPassword.trim() === "" || newPassword.trim() === 0) {
      alertInvalid(
        "Invalid input, make sure everything is filed correctly and try again!"
      );
    } else {
      UserService.changePassword(passwordObj).then((response) => {
        const responseFromServer = response.data;
        if (responseFromServer === "success") {
          alertSuccess("Successfully changed password!");
          getCurrentUser();
          handleClosePassModal();
        } else if (response.data.toString() === "fail") {
          alertInvalid("Your old and inserted password don't match! Try again");
        }
      });
    }
  };

  const handleSubmit = () => {
    //validacija unosa
    console.log(
      firstNameEdit +
        " " +
        lastNameEdit +
        " " +
        emailEdit +
        " " +
        usernameEdit +
        " " +
        phoneNumberEdit +
        " " +
        addressEdit
    );
    if (
      firstNameEdit.trim() === "" ||
      lastNameEdit.trim() === "" ||
      emailEdit.trim() === "" ||
      usernameEdit.trim() === "" ||
      phoneNumberEdit.trim() === "" ||
      addressEdit.trim() === ""
    ) {
      alertInvalid(
        "Invalid input, make sure everything is filed correctly and try again!"
      );
    } else if (validateEmail() === false) {
      alertInvalid("Invalid email! Make sure email is valid and try again!");
    } else if (!isValidNumber(phoneNumber)) {
      alertInvalid("Invalid phone number or it has less than 5 digits");
    } else {
      UserService.updateUser(userEdit)
        .then((response) => {
          const responseFromServer = response.data;
          if (responseFromServer === "success") {
            alertSuccess("Successfully edited profile!");
            getCurrentUser();
          } else if (response.data.toString() === "invalid") {
            alertInvalid(
              "Invalid input, make sure everything is filed correctly and try again!"
            );
          } else if (response.data.toString() === "emailNotUnique") {
            alertInvalid("Email already exists! Try again!");
          } else if (response.data.toString() === "usernameNotUnique") {
            alertInvalid("Username already exists! Try again!");
          }
        })
        .finally(() => handleClose());
    }
  };

  const validateEmail = () => {
    //treba bez ''
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(emailEdit);
  };

  const alertInvalid = (message) => {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: message,
    });
  };

  const alertSuccess = (message) => {
    Swal.fire({
      position: "top",
      icon: "success",
      title: message,
      showConfirmButton: false,
      timer: 1500,
    });
  };

  const isValidNumber = (input) => {
    // ^\d{5,}$: Uses a regular expression to ensure that the input consists of at least 5 digits.
   
    if (isNaN(input) || /^\d{5,}$/.test(input) === false) {
      return false;
    } else {
      return true;
    }
  };

  return (
    <>
      <div className="container">
        <div className="main-body">
          <div className="row gutters-sm">
            <div className="col-md-4 mb-3">
              <div className="card">
                <div className="card-body">
                  <div className="d-flex flex-column align-items-center text-center">
                    <img
                      src="https://bootdey.com/img/Content/avatar/avatar7.png"
                      alt="Admin"
                      className="rounded-circle"
                      width="150"
                    />
                    <div className="mt-3">
                      <h4>
                        {firstName} {lastName}
                      </h4>
                      <p className="text-secondary mb-1">
                        User
                      </p>
                      <p className="text-muted font-size-sm">{address}</p>
                      <button
                        id="changePassBtn"
                        className="btn btn-success"
                        onClick={handleShowPassModal}
                      >
                        Change password
                      </button>
                      {/* <Link className='btn btn-success' to={`/my-active-final-orders`}>Active orders</Link> */}
                      <Link
                        className="btn btn-danger"
                        style={{ marginLeft: "5px" }}
                        to={`/my-delivered-final-orders`}
                      >
                        Order history
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div className="card mt-3">
                
              </div>
            </div>
            <div className="col-md-8">
              <div className="card mb-3">
                <div className="card-body">
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">First name</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{firstName}</div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">Last name</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{lastName}</div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">Username</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{username}</div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">Email</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{email}</div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">Phone number</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{phoneNumber}</div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">Address</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{address}</div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h4 className="mb-0">Role</h4>
                    </div>
                    <div className="col-sm-9 text-secondary">{role}</div>
                  </div>
                  {/* <div className="row">
                        <div className="col-sm-3">
                        <h4 className="mb-0">Password</h4>
                        </div>
                        <div className="col-sm-9 text-secondary">
                            <label id="labelPassword">{password}</label>
                        </div>
                    </div> */}
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-12">
                      <button className="btn btn-success" onClick={handleShow}>
                        Edit profile
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* NE MOZE MODAL.DIALOG, MORA MODAL SAMO */}
      <Modal
        show={show}
        onHide={handleClose}
        dialogClassName="modalEditProfile"
        className="d-flex align-items-center justify-content-center"
      >
        <Modal.Header closeButton>
          <Modal.Title>Edit profile</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <EditMyProfileComponent user={user} handleSubmit={handleSubmit} />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Save changes
          </Button>
        </Modal.Footer>
      </Modal>

     
      <Modal
        show={showPassModal}
        onHide={handleClosePassModal}
        dialogClassName="modalEditPassword"
        className="d-flex align-items-center justify-content-center"
      >
        <Modal.Header closeButton>
          <Modal.Title>Edit password</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <EditPasswordComponent
            passwordObj={passwordObj}
            handleSubmitChangePass={handleSubmitChangePass}
          />
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSubmitChangePass}>
            Save changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default MyProfileComponent;
