import React from 'react'
import './searchInput.css'
import { Select, Input, Divider, Button, Icon } from 'semantic-ui-react'

const SearchInput = (props) => {
    const queryHistories = props.queryHistories.map(function(item, index){
        return <option value={item.query} key={index}/>
    });

    return (
        <div className='inputWrapper'>
            <Input type='text' placeholder='Search...' action>
                <Select compact options={props.options} defaultValue='전체' />
                <input id='searchQueryInput'
                    placeholder='보고 싶은 책을 찾아보세요'
                    list='history'
                    onKeyPress={props.onKeyPress}
                />
                <Button color='teal' onClick={() => alert()}>
                    <Icon name='search'/> 검색
                </Button>
            </Input>
            <datalist id='history'>
                {queryHistories}
            </datalist>
            <Divider />
        </div>
    )
}

export default SearchInput
