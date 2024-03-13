import React, { useState, useEffect } from "react";
import MealTypeService from "../../services/MealTypeService";
import { Form } from "react-bootstrap";
import styles from "./ListMealTypeComponent.css";

const CreateMealTypeComponent = (props) => {
  const mealType = props.mealType; 
  const selectedFile = props.file;
  

  const onChoseFile = (e) => {
    console.log(e.target.files[0]);
    selectedFile.setSelectedFile(e.target.files[0]);
  };

  return (
    <div>
      <div className="container-add-meal">
        <form>
          <div className="form-group mb-2">
            <label className="form-label">Type name: </label>
            <input
              type="text"
              placeholder="Insert name"
              name="typeName"
              className="form-control"
              value={mealType.typeName}
              onChange={(e) => mealType.setTypeName(e.target.value)}
            ></input>
          </div>

          <div className="form-group mb-2">
            <label className="form-label">Description: </label>
            <input
              type="text"
              placeholder="Insert description"
              name="description"
              className="form-control"
              value={mealType.description}
              onChange={(e) => mealType.setDescription(e.target.value)}
            ></input>
          </div>

          <div className="form-group mb-2">
            <label className="form-label">Upload image </label>
            <input
              type="file"
              placeholder="Insert price"
              name="image"
              className="form-control"
              onChange={(e) => onChoseFile(e)}
            ></input>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CreateMealTypeComponent;
