import { createSlice } from "@reduxjs/toolkit";

export const cartSlice = createSlice({
  name: "cart",
  initialState: [],

  // The `reducers` field lets us define reducers and generate associated actions
  reducers: {
    addItem: (state, action) => {
      let alreadyExistMeal = 0;

      for (let i = 0; i < state.length; i++) {
        if (state[i].mealId === action.payload.mealId) {
          state[i].quantity += action.payload.quantity;
          alreadyExistMeal = 1;
        }
      }
      if (alreadyExistMeal === 0) {
        state.push(action.payload);
      }
    },

    editItem: (state, action) => {
      for (let i = 0; i < state.length; i++) {
        if (state[i].mealId === action.payload.item.mealId) {
          state[i].quantity = action.payload.itemQuantity;
          return;
        }
      }
    },

    deleteItem: (state, action) => {
      for (let i = 0; i < state.length; i++) {
        console.log("usloo1: ", i, " ok: ", action.payload);

        if (state[i].mealId === action.payload) {
          state.splice(i, 1);
          console.log("usloo: ", i, " ok: ", action.payload);
        }
      }
    },

    deleteAllItems: (state) => {
      for (let i = 0; i < state.length; i++) {
        state.splice(i);
      }
    },
  },
 
});
export const { addItem, editItem, deleteItem, deleteAllItems } =
  cartSlice.actions;

export default cartSlice.reducer;
