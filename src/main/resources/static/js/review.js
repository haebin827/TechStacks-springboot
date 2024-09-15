/*
async function get1(bNo) {
    const result = await axios.get(`/review/list/${bNo}`)
    console.log(result);

    return result.data;
}

/!*
async function getList({bNo, page, size, goLast}) {
    const result = await axios.get(`/review/list/${bNo}`, {params: {page, size}})
    return result.data;
}*!/

async function getList({bNo, page, size, goLast}) {
    try {
        const result = await axios.get(`/review/list/${bNo}`, {params: {page, size}})

        if(goLast) {
            const total = result.data.total;
            const lastPage = parseInt(Math.ceil(total/size))
            return getList({bNo: bNo, page: lastPage, size: size})
        }
        console.log(result.data) // 서버에서 전달된 데이터를 확인
        return result.data
    } catch (error) {
        console.error('Error fetching the review list:', error)
    }
}

async function addReview(reviewObj) {
    try {
        const response = await axios.post('/review/', reviewObj);
        return response.data;
    } catch (error) {
        console.error('Error adding review:', error);
        alert('There was an error submitting your review. Please try again later.');
        // 오류가 발생한 경우, 적절한 값을 반환하거나 null을 반환할 수 있습니다.
        return null;
    }
}

async function getReview(rNo) {
    const response = await axios.get(`/review/${rNo}`)
    return response.data
}

async function modifyReview(reviewObj) {
    const response = await axios.put(`/review/${reviewObj.rNo}`, reviewObj)
    return response.data
}
*/
