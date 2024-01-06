import {useCallback} from "react";

function MyAccount() {


    const onRefChange = useCallback(node => {
        if (node === null) {
            console.log("no");
            // DOM node referenced by ref has been unmounted
        } else {
            console.log(node);
            // DOM node referenced by ref has changed and exists
        }
    }, []); // adjust deps

    return <h1 ref={onRefChange}>Hey</h1>;
}

export default MyAccount;

