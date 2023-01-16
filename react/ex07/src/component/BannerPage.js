import { Link, Route } from 'react-router-dom'
import BannerInsert from './BannerInsert'
import BannerList from './BannerList'
import BannerRead from './BannerRead'

const BannerPage = () => {
    return (
        <div>
            <div className='menu'>
                <Link to="/banners/list" >Banner List</Link>
                <Link to="/banners/insert" >Banner Resister</Link>
                <hr/>
            </div>
            <div>
                <Route path="/banners/insert" component={BannerInsert} />
                <Route path="/banners/list" component={BannerList} />
                <Route path="/banners/read/:id" component={BannerRead} />
            </div>
        </div>
    )
}

export default BannerPage